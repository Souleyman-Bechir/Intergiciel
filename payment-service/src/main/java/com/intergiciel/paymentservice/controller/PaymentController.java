package com.intergiciel.paymentservice.controller;

import com.intergiciel.paymentservice.dto.PaymentRequest;
import com.intergiciel.paymentservice.model.Payment;
import com.intergiciel.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public Payment createPayment(@RequestBody PaymentRequest request) {
        return paymentService.processPayment(request);
    }

    @GetMapping("/student/{studentId}")
    public List<Payment> getPaymentsByStudent(@PathVariable Long studentId) {
        return paymentService.getPaymentsByStudent(studentId);
    }

    @GetMapping("/semester/{semester}")
    public List<Payment> getPaymentsBySemester(@PathVariable String semester) {
        return paymentService.getPaymentsBySemester(semester);
    }
}
