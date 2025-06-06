package com.intergiciel.StudentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.intergiciel.StudentService.model.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}


