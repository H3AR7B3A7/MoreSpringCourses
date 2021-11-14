package com.pluralsight.conference.util;

import com.pluralsight.conference.model.Password;
import com.pluralsight.conference.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@Component
public class PasswordListener implements ApplicationListener<PasswordResetEvent> {

    private static final String SERVER_URL = "http://localhost:8080";
    
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private PasswordService passwordService;

    @Override
    public void onApplicationEvent(PasswordResetEvent passwordResetEvent) {
        try {
            this.resetPassword(passwordResetEvent);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void resetPassword(PasswordResetEvent passwordResetEvent) throws MessagingException {
        Password password = passwordResetEvent.getPassword();
        String token = UUID.randomUUID().toString();
        passwordService.createResetToken(password, token);
        
        String recipientAddress = password.getEmail();
        String subject = "Password Reset";
        String confirmationUrl = "/passwordReset?token=" + token;
        String message = "Reset password:";

        MimeMessage email = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(email, true);
        helper.setTo(recipientAddress);
        helper.setSubject(subject);
        helper.setText(message + "\r\n" + SERVER_URL + confirmationUrl);
        mailSender.send(email);
    }
}