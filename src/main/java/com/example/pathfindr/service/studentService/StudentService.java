package com.example.pathfindr.service.studentService;

import org.springframework.security.core.Authentication;

import com.example.pathfindr.model.Student;

public interface StudentService {

    void saveStudent(Student student);

    String generateToken();

    void verifyToken(String token, Authentication authentication, Student student);

    Student getAuthenticatedStudent(Authentication authentication, Student student);

    Student getLatesStudent();

}
