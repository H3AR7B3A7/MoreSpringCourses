package com.pluralsight.conference.service;

import com.pluralsight.conference.model.Account;
import com.pluralsight.conference.model.ConferenceUserDetails;
import com.pluralsight.conference.model.VerificationToken;
import com.pluralsight.conference.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account create(Account account) {
        return accountRepository.create(account);
    }

    @Override
    public void createVerificationToken(Account account, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUsername(account.getUsername());

        accountRepository.saveToken(verificationToken);
    }

    @Override
    public void confirmAccount(String token) {
        VerificationToken verificationToken = accountRepository.findByToken(token);
        if (verificationToken.getExpiryDate().after(new Date())) {
            Account account = accountRepository.findByUsername(verificationToken.getUsername());
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            ConferenceUserDetails userDetails =
                    new ConferenceUserDetails(account.getUsername(), account.getPassword(), authorities);
            accountRepository.createUserDetails(userDetails);
            accountRepository.createAuthorities(userDetails);
            accountRepository.delete(account);
            accountRepository.deleteToken(token);
        }
    }
}