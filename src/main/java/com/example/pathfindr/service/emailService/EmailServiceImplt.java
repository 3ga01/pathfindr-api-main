package com.example.pathfindr.service.emailService;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.pathfindr.model.Mentor;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

import com.example.pathfindr.repository.MentorRepository;

@Service
public class EmailServiceImplt implements EmailService {

    private JavaMailSender javaMailSender;

    public EmailServiceImplt(JavaMailSender javaMailSender) {

        this.javaMailSender = javaMailSender;

    }

    @Override
    public void sendSimpleEmail(String toEmail, String body, String subject) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);

        message.setSubject(subject);

        message.setText(body);

        message.setFrom("pathfindr81@gmail.com");

        javaMailSender.send(message);

    }

    @Autowired
    private MentorRepository mentorRepository;

    @Override
    public void sendEmailWithAttachment(String toEmail, String body, String subject, Long mentorId)
            throws MessagingException, IOException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        // Use the true flag to indicate you need a multipart message
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body);

        // Retrieve the mentor and their CV from the database
        Mentor mentor = mentorRepository.findById(mentorId).orElse(null);

        if (mentor != null) {
            // Extract CV data from the mentor entity
            byte[] cvData = mentor.getCv();

            // Create a DataSource from the CV data
            ByteArrayDataSource cvDataSource = new ByteArrayDataSource(cvData, "application/pdf");

            // Add the CV attachment
            helper.addAttachment("MentorCV.pdf", cvDataSource);

            // Send the email
            javaMailSender.send(mimeMessage);
        }

        else {

            throw new IllegalArgumentException("Mentor not found.");

        }

    }

}
