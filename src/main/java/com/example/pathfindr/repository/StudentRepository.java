package com.example.pathfindr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pathfindr.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByUsername(String username);

    Student findByEmail(String email);

    Student findTopByOrderByIdDesc();
}
