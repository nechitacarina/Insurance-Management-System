package com.project.insurancems.payload.request;

import com.project.insurancems.entity.ContractStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateContractRequest {
    private LocalDate effectiveDate;
    private LocalDate expirationDate;
    private double price;
    private double maxClaimAmount;
    private int idClient;
    private int idPolicy;
}
