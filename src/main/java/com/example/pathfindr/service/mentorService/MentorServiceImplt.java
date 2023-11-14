package com.example.pathfindr.service.mentorService;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pathfindr.model.Mentor;
import com.example.pathfindr.model.Role;
import com.example.pathfindr.repository.MentorRepository;
import com.example.pathfindr.repository.RoleRepository;
import com.example.pathfindr.service.emailService.EmailService;
import com.example.pathfindr.service.validationService.ValidationService;

import jakarta.mail.MessagingException;

@Service
public class MentorServiceImplt implements MentorService {

        @Autowired
        private MentorRepository mentorRepository;

        @Autowired
        private ValidationService validationService;

        @Autowired
        private EmailService emailService;

        @Autowired
        private RoleRepository roleRepository;

        @Autowired
        private PasswordGenerator passwordGenerator;

        @Override
        public void saveMentor(Mentor mentor) throws MessagingException, IOException {

                // Validate Mentor Input
                validationService.validateUsername(mentor.getUsername());
                validationService.validateEmail(mentor.getEmail());
                // validationService.validatePassword(mentor.getPassword());

                // Set Role
                Role role = roleRepository.findByName("MENTOR").get();
                mentor.setRoles(Collections.singletonList(role));

                String mentorPassword = passwordGenerator.generatePassword();

                // mentor.setPassword(mentorPassword);

                mentor.setPassword(new BCryptPasswordEncoder().encode(mentorPassword));

                mentor.setVerified("no");

                mentor.setSpecialty("Engineering");

                mentorRepository.save(mentor);

                // Send email to admin for verification
                emailService.sendEmailWithAttachment("omariemmanuel91@gmail.com", "Copy ofCV",
                                "Pathfindr Mentor Application",
                                mentor.getId());

                emailService.sendSimpleEmail(mentor.getEmail(),
                                "Application received successfully and under review.\n Feed back will be given within the next 24 to 72 hours\\n"
                                                +
                                                " Thank you.",
                                "Panthfindr Mentor Application");

        }

}
