package com.intergiciel.enrollmentservice.dto;

public class NotificationDto {

    private Long studentId;  // Ajout de l'ID de l'étudiant
    private Long courseId;   // Ajout de l'ID du cours
    private String semester; // Ajout du semestre
    private String toEmail;       // Destinataire
    private String subject;  // Sujet
    private String body;     // Corps du message

    public NotificationDto() {
    }

    // Constructeur avec les nouveaux champs
    public NotificationDto(Long studentId, Long courseId, String semester, String toEmail, String subject, String body) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.semester = semester;
        this.toEmail = toEmail;
        this.subject = subject;
        this.body = body;
    }

    // Getters et Setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setTo(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
