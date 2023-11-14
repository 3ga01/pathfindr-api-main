package com.example.pathfindr.service.mentorService;

import java.io.IOException;

import com.example.pathfindr.model.Mentor;

import jakarta.mail.MessagingException;

public interface MentorService {

        void saveMentor(Mentor mentor) throws MessagingException, IOException;

}
