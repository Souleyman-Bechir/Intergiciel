package com.intergiciel.paymentservice.service;

import com.intergiciel.paymentservice.dto.PaymentRequest;
import com.intergiciel.paymentservice.model.Payment;

import java.util.List;

public interface PaymentService {
    Payment processPayment(PaymentRequest request);
    List<Payment> getPaymentsByStudent(Long studentId);
    List<Payment> getPaymentsBySemester(String semester);
}
