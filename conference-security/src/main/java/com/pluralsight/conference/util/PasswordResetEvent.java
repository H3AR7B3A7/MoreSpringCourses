package com.pluralsight.conference.util;

import com.pluralsight.conference.model.Password;
import org.springframework.context.ApplicationEvent;

public class PasswordResetEvent extends ApplicationEvent {
    private Password password;
    
    public PasswordResetEvent(Password password) {
        super(password);
        this.password = password;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }
}