package com.intergiciel.enrollmentservice.dto;

/**
 * DTO représentant un cours dans le microservice d'inscription.
 */
public class CourseDto {
    private Long id;
    private String code;
    private String title;
    private String description;
    private int credits;

    public CourseDto() {
    }

    public CourseDto(Long id, String code, String title, String description, int credits) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.description = description;
        this.credits = credits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
