package com.intergiciel.CourseService.repository;

import com.intergiciel.CourseService.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // Tu peux ajouter des méthodes personnalisées ici si nécessaire
}
