package com.project.insurancems.payload.request;

import com.project.insurancems.entity.Client;
import com.project.insurancems.entity.Contract;
import com.project.insurancems.entity.Policy;
import com.project.insurancems.entity.User;
import lombok.Data;

@Data
public class CreateClientRequest {
    private Client client;
    private User user;
    private Contract contract;
    private int selectedPolicy;
}
