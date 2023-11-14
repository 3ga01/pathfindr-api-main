package com.example.pathfindr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pathfindr.model.Mentor;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {

    Mentor findByUsername(String username);

    Mentor findByEmail(String email);

    List<Mentor> findBySpecialty(String specialty);
}
