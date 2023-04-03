package com.project.insurancems.controller;

import com.project.insurancems.entity.Category;
import com.project.insurancems.entity.Policy;
import com.project.insurancems.security.UserDetailsImpl;
import com.project.insurancems.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @GetMapping("/count-vehicle")
    @PreAuthorize("hasRole('AGENT')")
    public int getTotalNumberOfVehiclePolicies() {
        return policyService.getTotalNumberOfVehiclePolicies();
    }

    @GetMapping("/count-property")
    @PreAuthorize("hasRole('AGENT')")
    public int getTotalNumberOfPropertyPolicies() {
        return policyService.getTotalNumberOfPropertyPolicies();
    }

    @GetMapping("/count-life")
    @PreAuthorize("hasRole('AGENT')")
    public int getTotalNumberOfLifePolicies() {
        return policyService.getTotalNumberOfLifePolicies();
    }

    @GetMapping("/byCategory")
    @PreAuthorize("hasRole('AGENT')")
    public List<Policy> getPoliciesByCategoryId(@RequestParam int id){
        return policyService.getPoliciesByCategoryId(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('AGENT')")
    public List<Policy> getAllPolicies(){
        return policyService.getAllPolicies();
    }

    @GetMapping("/loggedInClient-policyByCategory")
    public List<Policy> getLoggedInClientPolicyByCategory(@AuthenticationPrincipal UserDetailsImpl user, @RequestParam int id){
        return policyService.getLoggedInClientPoliciesByCategory(user, id);
    }
}
