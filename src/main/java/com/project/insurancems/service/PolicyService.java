package com.project.insurancems.service;

import com.project.insurancems.entity.Category;
import com.project.insurancems.entity.Client;
import com.project.insurancems.entity.Policy;
import com.project.insurancems.repository.ClientRepository;
import com.project.insurancems.repository.PolicyRepository;
import com.project.insurancems.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {

    @Autowired
    PolicyRepository policyRepository;

    @Autowired
    ClientRepository clientRepository;
    public int getTotalNumberOfVehiclePolicies(){
        return policyRepository.countVehiclePolicies();
    }

    public int getTotalNumberOfPropertyPolicies(){
        return policyRepository.countPropertyPolicies();
    }

    public int getTotalNumberOfLifePolicies(){
        return policyRepository.countLifePolicies();
    }

    public List<Policy> getPoliciesByCategoryId(int id){
        return policyRepository.getPoliciesByCategoryId(id);
    }

    public List<Policy> getAllPolicies(){
        return policyRepository.findAll();
    }

    public List<Policy> getLoggedInClientPoliciesByCategory(UserDetailsImpl user, int idCategory){
        int idUser = user.getId();
        Client client = clientRepository.getClientByUserId(idUser);
        int idClient = client.getId();
        return policyRepository.getClientPolicyByCategory(idClient, idCategory);
    }
}
