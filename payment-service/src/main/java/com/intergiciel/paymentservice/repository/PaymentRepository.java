package com.intergiciel.paymentservice.repository;

import com.intergiciel.paymentservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByStudentId(Long studentId);

    List<Payment> findBySemester(String semester);

    List<Payment> findByStudentIdAndSemester(Long studentId, String semester);
}


