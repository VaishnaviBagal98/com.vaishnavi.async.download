package com.vaishnavi.async.statement.download.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * This class is email service implemented to send email to user for notifications.
 *
 * @author Vaishnavi Bagal
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    /**
     * This method sends emails to user
     *
     * @param toEmail email ID of the receiver
     * @param subject subject line of email
     * @param body    email body
     */
    public void sendEmail(String toEmail, String subject, String body) {
        log.info("Inside email service");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vaishnavibagal1998@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);

        log.info("Email send successfully to {}", toEmail);

    }
}
