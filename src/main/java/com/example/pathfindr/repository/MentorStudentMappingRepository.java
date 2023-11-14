package com.example.pathfindr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pathfindr.model.MentorStudentMapping;

@Repository
public interface MentorStudentMappingRepository extends JpaRepository<MentorStudentMapping, Long>{

    
    

    
}
