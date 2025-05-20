package com.intergiciel.enrollmentservice.client;

import com.intergiciel.enrollmentservice.dto.StudentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "studentservice") 
public interface StudentClient {

    @GetMapping("/students/{id}") 
     // L'endpoint pour récupérer un étudiant par son ID
    StudentDto getStudentById(@PathVariable("id") Long id);  
}


