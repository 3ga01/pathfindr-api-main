package com.example.pathfindr.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pathfindr.model.Mentor;
import com.example.pathfindr.model.Student;
import com.example.pathfindr.repository.MentorRepository;
import com.example.pathfindr.repository.StudentRepository;

@Service
public class MatchingService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MentorRepository mentorRepository;

    public Mentor findRandomMentorWithCommonSpecialties(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            // Handle the case where the student is not found
            return null;
        }

        String studentSpecialty = student.getSpecialty();

        // Find mentors with the same specialty
        List<Mentor> matchingMentors = mentorRepository.findBySpecialty(studentSpecialty);

        if (matchingMentors.isEmpty()) {
            // No matching mentors found
            return null;
        }

        // Randomly choose a mentor
        Random random = new Random();
        int randomIndex = random.nextInt(matchingMentors.size());

        return matchingMentors.get(randomIndex);
    }
}
