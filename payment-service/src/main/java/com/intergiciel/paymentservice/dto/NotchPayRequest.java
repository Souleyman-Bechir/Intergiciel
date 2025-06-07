package com.intergiciel.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotchPayRequest {
    private String amount;
}
