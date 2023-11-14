package com.example.pathfindr.service.emailService;

import java.io.IOException;

import jakarta.mail.MessagingException;

public interface EmailService {

    void sendSimpleEmail(String toEmail, String body, String subject);

    void sendEmailWithAttachment(String toEmail, String body, String subject, Long mentorId)
            throws MessagingException, IOException;

}
