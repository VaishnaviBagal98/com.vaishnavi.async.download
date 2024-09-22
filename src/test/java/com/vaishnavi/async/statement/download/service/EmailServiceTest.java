package com.vaishnavi.async.statement.download.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.verify;

class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender emailSender;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendEmail() {
        String toEmail = "test@example.com";
        String subject = "Test Subject";
        String body = "Test Email Body";

        emailService.sendEmail(toEmail, subject, body);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vaishnavibagal1998@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        verify(emailSender).send(message);
    }
}
