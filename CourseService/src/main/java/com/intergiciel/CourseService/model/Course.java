package com.intergiciel.CourseService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String title;
    private String description;
    private int credits;

    // Constructeur par défaut (obligatoire pour JPA)
    public Course() {
    }

    // Constructeur sans ID (pour la création)
    public Course(String code, String title, String description, int credits) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.credits = credits;
    }

    // Constructeur complet (utile pour les tests ou DTO)
    public Course(Long id, String code, String title, String description, int credits) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.description = description;
        this.credits = credits;
    }

    // Getters et Setters
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
