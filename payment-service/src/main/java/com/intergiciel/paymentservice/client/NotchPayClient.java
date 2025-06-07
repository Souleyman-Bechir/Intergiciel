package com.intergiciel.paymentservice.client;

import com.intergiciel.paymentservice.dto.NotchPayResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "notchpay-client", url = "https://api.notchpay.co")
public interface NotchPayClient {

    @PostMapping("/payments")
    NotchPayResponse createPayment(
        @RequestHeader("Authorization") String authorization,
        @RequestParam("amount") double amount,
        @RequestParam("currency") String currency,
        @RequestParam("description") String description,
        @RequestParam("email") String email
    );
}
