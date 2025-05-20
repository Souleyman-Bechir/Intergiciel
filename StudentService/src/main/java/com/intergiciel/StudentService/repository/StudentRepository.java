package com.intergiciel.StudentService.repository;

import com.intergiciel.StudentService.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

