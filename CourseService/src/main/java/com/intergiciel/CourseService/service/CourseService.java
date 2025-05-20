package com.intergiciel.CourseService.service;

import com.intergiciel.CourseService.model.Course;
import com.intergiciel.CourseService.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public List<Course> getCoursesByIds(List<Long> ids) {
        return courseRepository.findAllById(ids);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        return courseRepository.findById(id).map(course -> {
            course.setCode(updatedCourse.getCode());
            course.setTitle(updatedCourse.getTitle());
            course.setDescription(updatedCourse.getDescription());
            course.setCredits(updatedCourse.getCredits());
            return courseRepository.save(course);
        }).orElseGet(() -> {
            updatedCourse.setId(id);
            return courseRepository.save(updatedCourse);
        });
    }
}

