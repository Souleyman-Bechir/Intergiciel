package com.intergiciel.paymentservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentId;

    @Column(nullable = false)
    private String semester;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String currency; // Ex: XAF, USD, EUR

    @Column(nullable = false)
    private String status; // e.g., PENDING, SUCCESS, FAILED

    @Column(unique = true, nullable = false)
    private String transactionReference;

    private LocalDateTime paymentDate;
}
