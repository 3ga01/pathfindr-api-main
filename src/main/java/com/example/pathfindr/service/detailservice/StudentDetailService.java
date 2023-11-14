package com.example.pathfindr.service.detailservice;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.pathfindr.model.Mentor;
import com.example.pathfindr.model.Role;
import com.example.pathfindr.model.Student;
import com.example.pathfindr.repository.MentorRepository;
import com.example.pathfindr.repository.StudentRepository;

@Service
public class StudentDetailService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Student student = studentRepository.findByEmail(email);

        Mentor mentor = mentorRepository.findByEmail(email);

        if (student == null && mentor == null) {

            throw new UsernameNotFoundException("User not found");

        }

        if (student != null) {

            return new org.springframework.security.core.userdetails.User(student.getEmail(), student.getPassword(),
                    mapRolesToAuthorities(student.getRoles()));

        }

        else {

            return new org.springframework.security.core.userdetails.User(mentor.getEmail(), mentor.getPassword(),
                    mapRolesToAuthorities(mentor.getRoles()));
        }

    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }

}
