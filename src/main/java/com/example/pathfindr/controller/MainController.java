package com.example.pathfindr.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.pathfindr.model.Mentor;
import com.example.pathfindr.model.MentorStudentMapping;
import com.example.pathfindr.model.Student;
import com.example.pathfindr.model.SurveyResponse;
import com.example.pathfindr.repository.MentorStudentMappingRepository;
import com.example.pathfindr.repository.RoleRepository;
import com.example.pathfindr.repository.StudentRepository;
import com.example.pathfindr.repository.SurveyResponseRepository;
import com.example.pathfindr.service.MatchingService;
import com.example.pathfindr.service.mentorService.MentorService;
import com.example.pathfindr.service.studentService.StudentService;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;

import jakarta.mail.MessagingException;

@RestController
public class MainController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    MentorService mentorService;

    @Autowired
    SurveyResponseRepository surveyResponseRepository;

    @Autowired
    MentorStudentMappingRepository mentorStudentRepository;

    @Autowired
    MatchingService matchingService;

    @PostMapping("/signUpStudent")
    public ResponseEntity<String> addNewUser(@RequestBody Student student) {

        try {

            studentService.saveStudent(student);
            return ResponseEntity.ok("Registration successful");
        }

        catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PostMapping("/mentorApplication")
    public ResponseEntity<String> handleMentorApplication(

            @RequestParam("username") String username,

            @RequestParam("email") String email,

            @RequestParam("specialty") String specialty,

            @RequestPart("cv") MultipartFile cv) throws IOException, MessagingException {

        Mentor mentor = new Mentor();

        mentor.setUsername(username);

        mentor.setEmail(email);

        mentor.setSpecialty(specialty);

        mentor.setCv(cv.getBytes());

        try {

            mentorService.saveMentor(mentor);

            return ResponseEntity.ok("Mentor added successfully");

        } catch (Exception e) {

            return ResponseEntity.badRequest().body("Error: " + e.getMessage());

        }

    }

    // @PostMapping("/processSurvey")
    // public String processSurvey(@RequestBody SurveyResponse surveyResponse) {

    // String[] fieldNames = {

    // "Engineering",

    // "Business Analysis",

    // "Medicine",

    // "Art",

    // "Social Sciences",

    // "Humanities",

    // "Information Technology"
    // };

    // int[] scores = new int[fieldNames.length];

    // scores[surveyResponse.getAnswerQuestion1()]++;
    // scores[surveyResponse.getAnswerQuestion2()]++;
    // scores[surveyResponse.getAnswerQuestion3()]++;
    // scores[surveyResponse.getAnswerQuestion4()]++;
    // scores[surveyResponse.getAnswerQuestion5()]++;
    // scores[surveyResponse.getAnswerQuestion6()]++;
    // scores[surveyResponse.getAnswerQuestion7()]++;
    // scores[surveyResponse.getAnswerQuestion8()]++;
    // scores[surveyResponse.getAnswerQuestion9()]++;

    // List<String> topFields = new ArrayList<>();

    // for (int i = 0; i < 3; i++) {

    // int maxScore = -1;

    // int maxIndex = -1;

    // for (int j = 0; j < scores.length; j++) {

    // if (scores[j] > maxScore) {

    // maxScore = scores[j];

    // maxIndex = j;
    // }
    // }

    // if (maxIndex != -1) {

    // topFields.add(fieldNames[maxIndex]);

    // scores[maxIndex] = -1; // Mark as visited
    // }
    // }

    // return String.join(", ", topFields);
    // }

    @PostMapping("/processSurvey")
    public String processSurvey(@RequestBody SurveyResponse surveyResponse) {

        String[] fieldNames = {
                "Engineering",
                "Business Analysis",
                "Medicine",
                "Art",
                "Social Sciences",
                "Humanities",
                "Information Technology"
        };

        int[] scores = new int[fieldNames.length];

        scores[surveyResponse.getAnswerQuestion1()]++;
        scores[surveyResponse.getAnswerQuestion2()]++;
        scores[surveyResponse.getAnswerQuestion3()]++;
        scores[surveyResponse.getAnswerQuestion4()]++;
        scores[surveyResponse.getAnswerQuestion5()]++;
        scores[surveyResponse.getAnswerQuestion6()]++;
        scores[surveyResponse.getAnswerQuestion7()]++;
        scores[surveyResponse.getAnswerQuestion8()]++;
        scores[surveyResponse.getAnswerQuestion9()]++;

        int maxScore = -1;
        int maxIndex = -1;

        for (int j = 0; j < scores.length; j++) {
            if (scores[j] > maxScore) {
                maxScore = scores[j];
                maxIndex = j;
            }
        }

        Student newStudent = studentService.getLatesStudent();

        surveyResponse.setStudent(newStudent);

        newStudent.setSpecialty(fieldNames[maxIndex]);

        surveyResponse.setTopField(fieldNames[maxIndex]);

        surveyResponseRepository.save(surveyResponse);

        MentorStudentMapping mentorStudentMapping = new MentorStudentMapping();

        mentorStudentMapping.setStudent(surveyResponse.getStudent());

        mentorStudentMapping.setMentor(surveyResponse.getStudent().getMentor());

        mentorStudentMapping.setSpecialty(fieldNames[maxIndex]);

        mentorStudentRepository.save(mentorStudentMapping);

        return fieldNames[maxIndex];
    }

}
