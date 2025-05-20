package com.intergiciel.enrollmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@EntityScan(basePackages = "com.intergiciel.enrollmentservice.model")
@EnableJpaRepositories(basePackages = "com.intergiciel.enrollmentservice.repository")
public class EnrollmentserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnrollmentserviceApplication.class, args);
    }

}

