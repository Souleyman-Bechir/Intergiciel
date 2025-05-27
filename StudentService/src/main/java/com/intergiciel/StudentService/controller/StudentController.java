package com.intergiciel.StudentService.controller;

import com.intergiciel.StudentService.model.Student;
import com.intergiciel.StudentService.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Création avec vérification d'unicité par email
    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        Optional<Student> existingStudent = studentService.getStudentByEmail(student.getEmail());
        if (existingStudent.isPresent()) {
            return ResponseEntity.status(409).body("Un étudiant avec cet email existe déjà.");
        }
        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
public ResponseEntity<?> updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
    Optional<Student> existingStudent = studentService.getStudentById(id);
    if (existingStudent.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    // Vérifier si l'email que l'on veut mettre à jour est déjà utilisé par un autre étudiant
    Optional<Student> studentWithEmail = studentService.getStudentByEmail(student.getEmail());
    if (studentWithEmail.isPresent() && !studentWithEmail.get().getId().equals(id)) {
        // Si email déjà pris par un autre étudiant (différent de celui qu'on met à jour)
        return ResponseEntity.status(409).body("Cet email est déjà utilisé par un autre étudiant.");
    }

    // On peut mettre à jour
    student.setId(id);
    Student updatedStudent = studentService.saveStudent(student);
    return ResponseEntity.ok(updatedStudent);
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
