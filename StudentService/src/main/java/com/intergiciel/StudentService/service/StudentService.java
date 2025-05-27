package com.intergiciel.StudentService.service;

import com.intergiciel.StudentService.model.Student;
import com.intergiciel.StudentService.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Récupérer tous les étudiants
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Récupérer un étudiant par son ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Récupérer un étudiant par son email
    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    // Créer un nouvel étudiant
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // Mettre à jour un étudiant avec vérification email unique
    public Optional<Student> updateStudent(Long id, Student updatedStudent) {
        // Vérifier que l'email n'est pas déjà utilisé par un autre étudiant
        Optional<Student> studentWithEmail = studentRepository.findByEmail(updatedStudent.getEmail());
        if (studentWithEmail.isPresent() && !studentWithEmail.get().getId().equals(id)) {
            // email déjà utilisé par un autre, on ne fait pas la mise à jour
            return Optional.empty();
        }

        // Mettre à jour si l'étudiant existe
        return studentRepository.findById(id).map(student -> {
            student.setNom(updatedStudent.getNom());
            student.setPrenom(updatedStudent.getPrenom());
            student.setEmail(updatedStudent.getEmail());
            return studentRepository.save(student);
        });
    }

    // Supprimer un étudiant
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
