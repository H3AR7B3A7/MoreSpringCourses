package com.pluralsight.conference.model;

import java.util.Date;

public class VerificationToken {
    public static final int EXPIRATION = 60 * 24;

    private String token;
    private String username;
    private Date expiryDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date calculateExpiryDate(int expiryTimeInMinutes) {
        return new Date(new Date().getTime() + ((long) expiryTimeInMinutes * 60 * 1000));
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
//        return cal.getTime();
    }
}