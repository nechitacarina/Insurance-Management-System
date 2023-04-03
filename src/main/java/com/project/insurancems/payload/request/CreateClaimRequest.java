package com.project.insurancems.payload.request;

import lombok.Data;

@Data
public class CreateClaimRequest {
    private int category;
    private int policy;
    private int coverage;
    private double amount;

}
