package com.intergiciel.enrollmentservice.dto;

public class EnrollmentRequest {
    private Long studentId;
    private Long courseId;

    // Constructeur vide
    public EnrollmentRequest() {
    }

    // Getters
    public Long getStudentId() {
        return studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    // Setters
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
