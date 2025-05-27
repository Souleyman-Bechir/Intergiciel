package com.intergiciel.enrollmentservice.dto;

import java.util.List;

public class MultiEnrollmentRequest {
    private Long studentId;
    private List<Long> courseIds;
    private Integer semester;

    // Constructeur vide
    public MultiEnrollmentRequest() {}

    // Getters
    public Long getStudentId() {
        return studentId;
    }

    public List<Long> getCourseIds() {
        return courseIds;
    }

    public Integer getSemester() {
        return semester;
    }

    // Setters
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setCourseIds(List<Long> courseIds) {
        this.courseIds = courseIds;
    }
    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}
