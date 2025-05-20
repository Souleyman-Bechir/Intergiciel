package com.intergiciel.enrollmentservice.client;

import com.intergiciel.enrollmentservice.dto.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "CourseService")
public interface CourseClient {

    @GetMapping("/courses/{id}")
    CourseDto getCourseById(@PathVariable("id") Long id);

    // Nouveau : récupérer plusieurs cours via une liste d'IDs en paramètre
    @GetMapping("/courses/batch")
    List<CourseDto> getCoursesByIds(@RequestParam("ids") List<Long> ids);
}


