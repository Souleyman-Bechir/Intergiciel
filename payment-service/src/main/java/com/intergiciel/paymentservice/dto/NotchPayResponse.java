package com.intergiciel.paymentservice.dto;

import lombok.Data;

@Data
public class NotchPayResponse {
    private String reference;
    private String status;
    private String authorizationUrl;
}
