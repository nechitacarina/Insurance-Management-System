package com.project.insurancems.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "claims")
@Data
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_claim")
    private int id;

    @Column(nullable = false, length = 35)
    @Enumerated(EnumType.STRING)
    private ClaimStatus status;

    @Column(name = "date_of_submission",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate submissionDate;

    @Column(nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_policy")
    private Policy policy;

    @ManyToOne
    @JoinColumn(name = "id_coverage")
    private Coverage coverage;
}
