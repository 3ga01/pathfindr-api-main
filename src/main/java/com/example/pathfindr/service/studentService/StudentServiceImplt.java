package com.example.pathfindr.service.studentService;

import java.util.Collections;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pathfindr.model.Role;
import com.example.pathfindr.model.Student;
import com.example.pathfindr.repository.RoleRepository;
import com.example.pathfindr.repository.StudentRepository;
import com.example.pathfindr.service.emailService.EmailService;
import com.example.pathfindr.service.validationService.ValidationService;

@Service
public class StudentServiceImplt implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void saveStudent(Student student) {
        // Validate User Input
        validationService.validateUsername(student.getUsername());
        // validationService.checkIfUserExists(student);
        validationService.validateEmail(student.getEmail());

        validationService.validatePassword(student.getPassword());

        // Hash the password
        student.setPassword(new BCryptPasswordEncoder().encode(student.getPassword()));

        // Set Role
        Role role = roleRepository.findByName("STUDENT").get();
        student.setRoles(Collections.singletonList(role));

        // generate token
        String token = generateToken();

        // save token to current user object
        student.setToken(token);

        // send token to user as email
        emailService.sendSimpleEmail(student.getEmail(), "Token: " + token, "Pathfindr Token");

        student.setSpecialty("incoming");

        // Save the student
        studentRepository.save(student);

    }

    @Override
    public String generateToken() {
        Random random = new Random();
        int tokenValue = 100000 + random.nextInt(900000);
        return String.valueOf(tokenValue);
    }

    @Override
    public void verifyToken(String token, Authentication authentication, Student student) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verifyToken'");
    }

    @Override
    public Student getAuthenticatedStudent(Authentication authentication, Student student) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAuthenticatedStudent'");
    }

    @Override
    public Student getLatesStudent() {

        return studentRepository.findTopByOrderByIdDesc();
    }

}
