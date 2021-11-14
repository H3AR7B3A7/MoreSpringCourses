package com.pluralsight.conference.util;

import com.pluralsight.conference.model.Account;
import org.springframework.context.ApplicationEvent;

public class CreateAccountEvent extends ApplicationEvent {

    private final Account account;

    public CreateAccountEvent(Account account) {
        super(account);
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}