package com.intergiciel.paymentservice.service.impl;

import com.intergiciel.paymentservice.client.NotchPayClient;
import com.intergiciel.paymentservice.dto.NotchPayResponse;
import com.intergiciel.paymentservice.dto.PaymentRequest;
import com.intergiciel.paymentservice.model.Payment;
import com.intergiciel.paymentservice.repository.PaymentRepository;
import com.intergiciel.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final NotchPayClient notchPayClient;

    @Value("${notchpay.secret.key}")
    private String notchPayApiKey;

    @Override
    public Payment processPayment(PaymentRequest request) {
        // Appel à l’API NotchPay avec la clé secrète
        NotchPayResponse response = notchPayClient.createPayment(
                notchPayApiKey,
                request.getAmount(),
                request.getCurrency(),
                request.getDescription(),
                request.getEmail()
        );

        Payment payment = new Payment();
        payment.setStudentId(request.getStudentId());
        payment.setSemester(request.getSemester());
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setStatus("PENDING");
        payment.setPaymentDate(LocalDateTime.now());
        payment.setTransactionReference(response.getReference());

        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPaymentsByStudent(Long studentId) {
        return paymentRepository.findByStudentId(studentId);
    }

    @Override
    public List<Payment> getPaymentsBySemester(String semester) {
        return paymentRepository.findBySemester(semester);
    }
}
