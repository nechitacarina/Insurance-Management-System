package com.project.insurancems.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contracts")
@Data
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contract")
    private int id;

    @Column(name = "effective_date", nullable = false)
    @DateTimeFormat
    private LocalDate effectiveDate;

    @Column(name = "expiration_date", nullable = false)
    @DateTimeFormat
    private LocalDate expirationDate;

    @Column(nullable = false)
    private double price;

    @Column(name = "max_claim_amount", nullable = false)
    private double maxClaimAmount;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_policy")
    private Policy policy;
}
