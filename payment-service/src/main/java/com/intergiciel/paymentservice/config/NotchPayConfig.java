package com.intergiciel.paymentservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "notchpay")
@Data
public class NotchPayConfig {
    private String publicKey;
    private String secretKey;
    private String apiUrl;
}

