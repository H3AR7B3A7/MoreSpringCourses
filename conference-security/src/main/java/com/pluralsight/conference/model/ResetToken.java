package com.pluralsight.conference.model;

import java.util.Date;

public class ResetToken {
    
    public static final int EXPIRATION = 60 * 24;
    
    private String token;
    private String email;
    private Date expiryDate;
    private String username;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date calculateExpiryDate(int expiryTimeInMinutes) {
        return new Date(new Date().getTime() + ((long) expiryTimeInMinutes * 60 * 1000));
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
//        return cal.getTime();
    }
}