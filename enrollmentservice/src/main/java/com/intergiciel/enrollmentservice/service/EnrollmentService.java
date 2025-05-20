package com.intergiciel.enrollmentservice.service;

import com.intergiciel.enrollmentservice.client.CourseClient;
import com.intergiciel.enrollmentservice.client.StudentClient;
import com.intergiciel.enrollmentservice.client.NotificationClient;
import com.intergiciel.enrollmentservice.dto.CourseDto;
import com.intergiciel.enrollmentservice.dto.StudentDto;
import com.intergiciel.enrollmentservice.dto.NotificationDto;
import com.intergiciel.enrollmentservice.model.Enrollment;
import com.intergiciel.enrollmentservice.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentClient studentClient;

    @Autowired
    private CourseClient courseClient;

    @Autowired
    private NotificationClient notificationClient;

    // Créer une inscription avec vérifications et envoi de notification
    @Transactional
    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enroll(enrollment.getStudentId(), enrollment.getCourseId());
    }

    // Inscription à partir des IDs (utilisée par MultiEnrollmentRequest ou EnrollmentRequest)
    @Transactional
    public Enrollment enroll(Long studentId, Long courseId) {
        System.out.println("Début de l'inscription avec studentId = " + studentId + ", courseId = " + courseId);

        if (studentId == null) {
            throw new RuntimeException("L'ID de l'étudiant est null, impossible de récupérer l'étudiant.");
        }
        if (courseId == null) {
            throw new RuntimeException("L'ID du cours est null, impossible de récupérer le cours.");
        }

        // Vérifier si l'étudiant existe
        StudentDto student = studentClient.getStudentById(studentId);
        if (student == null) {
            throw new RuntimeException("Étudiant avec ID " + studentId + " non trouvé.");
        }

        // Vérifier si le cours existe
        CourseDto course = courseClient.getCourseById(courseId);
        if (course == null) {
            throw new RuntimeException("Cours avec ID " + courseId + " non trouvé.");
        }

        // Vérifier si déjà inscrit
        boolean alreadyEnrolled = enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId);
        if (alreadyEnrolled) {
            throw new RuntimeException("L'étudiant est déjà inscrit à ce cours.");
        }

        // Enregistrer
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(courseId);
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        System.out.println("Inscription enregistrée avec succès : " + savedEnrollment.getId());

        // Notification
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setStudentId(studentId);
        notificationDto.setCourseId(courseId);
        notificationDto.setSemester("1");  // adapter selon besoin

        notificationClient.sendNotification(notificationDto);

        System.out.println("Notification envoyée.");

        return savedEnrollment;
    }

    // Récupérer toutes les inscriptions
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    // Récupérer une inscription par son ID
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscription non trouvée avec ID " + id));
    }

    // Mettre à jour une inscription
    public Enrollment updateEnrollment(Long id, Enrollment updatedEnrollment) {
        Enrollment existing = getEnrollmentById(id);
        existing.setStudentId(updatedEnrollment.getStudentId());
        existing.setCourseId(updatedEnrollment.getCourseId());
        return enrollmentRepository.save(existing);
    }

    // Supprimer une inscription
    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }
}

