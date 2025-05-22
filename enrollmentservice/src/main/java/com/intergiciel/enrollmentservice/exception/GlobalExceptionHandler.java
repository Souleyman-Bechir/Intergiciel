package com.intergiciel.enrollmentservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // Retourner juste le message d’erreur, sans la stack trace ni autre info
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)  // ou autre code HTTP approprié
                .body(ex.getMessage());
    }

    @ExceptionHandler(StudentNotFoundException.class)
public ResponseEntity<String> handleStudentNotFound(StudentNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
}

@ExceptionHandler(CourseNotFoundException.class)
public ResponseEntity<String> handleCourseNotFound(CourseNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
}


}

