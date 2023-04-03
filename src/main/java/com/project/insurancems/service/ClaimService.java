package com.project.insurancems.service;

import com.project.insurancems.entity.*;
import com.project.insurancems.payload.request.CreateClaimRequest;
import com.project.insurancems.repository.*;
import com.project.insurancems.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {
    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CoverageRepository coverageRepository;

    @Autowired
    private PolicyRepository policyRepository;

    public int getTotalNumberOfPendingClaims(){
        return claimRepository.getTotalNumberOfPendingClaims();
    }
    public List<Claim> getClaimByClientId(int id){
        return claimRepository.findClaimByClient_Id(id);
    }

    public List<Claim> getAuthenticatedClientClaims(UserDetailsImpl user){
        int idUser = user.getId();
        return claimRepository.findAuthenticatedUserClaims(idUser);
    }

    public void createClaim(UserDetailsImpl user, CreateClaimRequest claimRequest){
        int idUser = user.getId();
        Client client = clientRepository.getClientByUserId(idUser);
        Claim claim = new Claim();
        claim.setStatus(ClaimStatus.PENDING);
        claim.setSubmissionDate(LocalDate.now());
        claim.setClient(client);
        claim.setAmount(claimRequest.getAmount());
        int idCoverage = claimRequest.getCoverage();
        Optional<Coverage> coverage = coverageRepository.findById(idCoverage);
        claim.setCoverage(coverage.get());
        int idPolicy = claimRequest.getPolicy();
        Optional<Policy> policy = policyRepository.findById(idPolicy);
        claim.setPolicy(policy.get());
        claimRepository.save(claim);
    }

    public void acceptClaim(int id){
        Optional<Claim> claim = claimRepository.findById(id);
        claim.get().setStatus(ClaimStatus.ACCEPTED);
        claimRepository.save(claim.get());
    }

    public void rejectClaim(int id){
        Optional<Claim> claim = claimRepository.findById(id);
        claim.get().setStatus(ClaimStatus.REJECTED);
        claimRepository.save(claim.get());
    }

    public int countLoggedInClientClaims(UserDetailsImpl user){
        int idUser = user.getId();
        Client client = clientRepository.getClientByUserId(idUser);
        int idClient = client.getId();
        return claimRepository.countClaimByClient_Id(idClient);
    }

    public int countLoggedInClientAcceptedClaims(UserDetailsImpl user){
        int idUser = user.getId();
        Client client = clientRepository.getClientByUserId(idUser);
        int idClient = client.getId();
        return claimRepository.countAcceptedClaimsByClient_Id(idClient);
    }

    public int countLoggedInClientRejectedClaims(UserDetailsImpl user){
        int idUser = user.getId();
        Client client = clientRepository.getClientByUserId(idUser);
        int idClient = client.getId();
        return claimRepository.countRejectedClaimsByClient_Id(idClient);
    }
}
