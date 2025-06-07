package com.intergiciel.paymentservice.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long studentId;
    private String semester;
    private Double amount;
    private String currency;
    private String description;
    private String email;
}

