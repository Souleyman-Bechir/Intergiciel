package com.intergiciel.StudentService.service;

import com.intergiciel.StudentService.model.Student;
import com.intergiciel.StudentService.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
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

    // Créer un nouvel étudiant
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // Mettre à jour un étudiant
    public Optional<Student> updateStudent(Long id, Student updatedStudent) {
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
