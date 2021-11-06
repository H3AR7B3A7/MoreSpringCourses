package com.pluralsight.conference.util;

import com.pluralsight.conference.model.Account;
import org.springframework.context.ApplicationEvent;

public class OnCreateAccountEvent extends ApplicationEvent {

    private final Account account;

    public OnCreateAccountEvent(Account account) {
        super(account);
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}