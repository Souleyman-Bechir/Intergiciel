package com.intergiciel.enrollmentservice.controller;

import com.intergiciel.enrollmentservice.dto.EnrollmentRequest;
import com.intergiciel.enrollmentservice.dto.MultiEnrollmentRequest;
import com.intergiciel.enrollmentservice.model.Enrollment;
import com.intergiciel.enrollmentservice.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    // ➤ Inscription à un seul cours via DTO
    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody EnrollmentRequest request) {
        Enrollment enrollment = enrollmentService.enroll(request.getStudentId(), request.getCourseId(), request.getSemester());
        return ResponseEntity.ok(enrollment);
    }

    // ➤ Inscription à plusieurs cours avec gestion des erreurs
@PostMapping("/multi")
public ResponseEntity<?> enrollMultiple(@RequestBody MultiEnrollmentRequest request) {
    List<Long> successful = new java.util.ArrayList<>();
    List<String> errors = new java.util.ArrayList<>();
    Integer semester = request.getSemester(); 

    for (Long courseId : request.getCourseIds()) {
        try {
            enrollmentService.enroll(request.getStudentId(), courseId, semester);
            successful.add(courseId);
        } catch (Exception e) {
            errors.add("Cours " + courseId + " : " + e.getMessage());
        }
    }

    return ResponseEntity.ok().body(
        java.util.Map.of(
            "inscriptionsRéussies", successful,
            "erreurs", errors
        )
    );
}


    // ➤ Récupérer tous les enrollments
    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    // ➤ Récupérer un enrollment par ID
    @GetMapping("/{id}")
    public Enrollment getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id);
    }

    // ➤ Mettre à jour un enrollment
    @PutMapping("/{id}")
    public Enrollment updateEnrollment(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        return enrollmentService.updateEnrollment(id, enrollment);
    }

    // ➤ Supprimer un enrollment
    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
    }
}

