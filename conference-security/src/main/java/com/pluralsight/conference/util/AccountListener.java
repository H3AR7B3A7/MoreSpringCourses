package com.pluralsight.conference.util;

import com.pluralsight.conference.model.Account;
import com.pluralsight.conference.service.AccountService;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@Component
public class AccountListener implements ApplicationListener<OnCreateAccountEvent> {

    private static final String SERVER_URL = "http://localhost:8080/";

    private final AccountService accountService;
    private final JavaMailSender mailSender;

    public AccountListener(AccountService accountService, JavaMailSender mailSender) {
        this.accountService = accountService;
        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(OnCreateAccountEvent onCreateAccountEvent) {
        try {
            confirmCreateAccount(onCreateAccountEvent);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void confirmCreateAccount(OnCreateAccountEvent onCreateAccountEvent) throws MessagingException {
        Account account = onCreateAccountEvent.getAccount();
        String token = UUID.randomUUID().toString();
        accountService.createVerificationToken(account, token);
        String recipientAddress = account.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = "accountConfirm?token=" + token;
        String message = "To confirm your account, please click the link below:\r\n"
                + SERVER_URL + confirmationUrl;

//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo(recipientAddress);
//        email.setSubject(subject);
//        email.setText(message);
//        mailSender.send(email);

        MimeMessage email = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(email, true);
        helper.setTo(recipientAddress);
        helper.setSubject(subject);
        helper.setText(message);
        mailSender.send(email);
    }
}