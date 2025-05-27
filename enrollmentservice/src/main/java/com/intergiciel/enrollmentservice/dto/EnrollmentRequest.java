package com.intergiciel.enrollmentservice.dto;

public class EnrollmentRequest {
    private Long studentId;
    private Long courseId;
    private Integer semester;

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

    public Integer getSemester() {
        return semester; // ✅ Getter manquant

    }

    // Setters
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setSemester(Integer semester) {
        this.semester = semester; // ✅ Setter manquant
    }
}
