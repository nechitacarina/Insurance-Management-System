package com.project.insurancems.controller;

import com.project.insurancems.entity.Claim;
import com.project.insurancems.payload.request.CreateClaimRequest;
import com.project.insurancems.security.UserDetailsImpl;
import com.project.insurancems.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @GetMapping("/count")
    @PreAuthorize("hasRole('AGENT')")
    public int getTotalNumberOfPendingClaims(){
        return claimService.getTotalNumberOfPendingClaims();
    }
    @GetMapping("/client/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public List<Claim> getClaimsByClientId(@PathVariable int id){
        return claimService.getClaimByClientId(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('CLIENT')")
    public List<Claim> getAuthenticatedClientClaims(@AuthenticationPrincipal UserDetailsImpl user){
        return claimService.getAuthenticatedClientClaims(user);
    }

    @PostMapping
    @PreAuthorize("hasRole('CLIENT')")
    public void createClaim(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody CreateClaimRequest claimRequest){
        claimService.createClaim(user, claimRequest);
    }

    @PutMapping("/accept/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public void acceptClaim(@PathVariable("id") int id){
        claimService.acceptClaim(id);
    }

    @PutMapping("/reject/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public void rejectClaim(@PathVariable("id") int id){
        claimService.rejectClaim(id);
    }

    @GetMapping("/count-loggedin-client")
    @PreAuthorize("hasRole('CLIENT')")
    public int getTotalNumberOfClaims(@AuthenticationPrincipal UserDetailsImpl user){
        return claimService.countLoggedInClientClaims(user);
    }

    @GetMapping("/count-accepted-loggedin-client")
    @PreAuthorize("hasRole('CLIENT')")
    public int getTotalNumberOfAcceptedClaims(@AuthenticationPrincipal UserDetailsImpl user){
        return claimService.countLoggedInClientAcceptedClaims(user);
    }

    @GetMapping("/count-rejected-loggedin-client")
    @PreAuthorize("hasRole('CLIENT')")
    public int getTotalNumberOfRejectedClaims(@AuthenticationPrincipal UserDetailsImpl user){
        return claimService.countLoggedInClientRejectedClaims(user);
    }
}
