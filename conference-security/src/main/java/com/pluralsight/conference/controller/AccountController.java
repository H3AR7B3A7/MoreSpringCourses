package com.pluralsight.conference.controller;

import com.pluralsight.conference.model.Account;
import com.pluralsight.conference.service.AccountService;
import com.pluralsight.conference.util.OnCreateAccountEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    private final AccountService accountService;
    private final PasswordEncoder encoder;
    private final ApplicationEventPublisher eventPublisher;

    public AccountController(AccountService accountService, PasswordEncoder encoder, ApplicationEventPublisher eventPublisher) {
        this.accountService = accountService;
        this.encoder = encoder;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping("account")
    public String getRegistration(@ModelAttribute("account") Account account) {
        return "account";
    }

    @PostMapping("account")
    public String postRegistration(@ModelAttribute("account") Account account, BindingResult bindingResult) {

        account.setPassword(encoder.encode(account.getPassword()));
        account = accountService.create(account);
        eventPublisher.publishEvent(new OnCreateAccountEvent(account));
        
        return "redirect:account";
    }
    
    @GetMapping("accountConfirm")
    public String confirmAccount(@RequestParam("token") String token) {
        accountService.confirmAccount(token);
        return "accountConfirmed";
    }
}