package com.project.insurancems.controller;

import com.project.insurancems.entity.Contract;
import com.project.insurancems.payload.request.CreateContractRequest;
import com.project.insurancems.security.UserDetailsImpl;
import com.project.insurancems.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping("/count-active")
    @PreAuthorize("hasRole('AGENT')")
    public int getTotalNumberOfActiveContracts() {
        return contractService.getTotalNumberOfActiveContracts();
    }

    @GetMapping("/count-expired")
    @PreAuthorize("hasRole('AGENT')")
    public int getTotalNumberOfExpiredContracts() {
        return contractService.getTotalNumberOfExpiredContracts();
    }

    @GetMapping("/client/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public List<Contract> getContractsByClientId(@PathVariable int id){
        return contractService.getContractsByClientId(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('AGENT')")
    public void createContract(@RequestBody CreateContractRequest contract){
        contractService.createContract(contract);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public void extendContractValability(@PathVariable("id") int id, @RequestBody Contract contract){
        contractService.updateContractAvability(id, contract);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public Contract getContractById(@PathVariable("id") int id){
        return contractService.getContractById(id);
    }

    @GetMapping("/count-loggedin-client")
    @PreAuthorize("hasRole('CLIENT')")
    public int getTotalNumberOfContracts(@AuthenticationPrincipal UserDetailsImpl user){
        return contractService.countLoggedInClientContracts(user);
    }

    @GetMapping("/count-active-loggedin-client")
    @PreAuthorize("hasRole('CLIENT')")
    public int getTotalNumberOfActiveContracts(@AuthenticationPrincipal UserDetailsImpl user){
        return contractService.countLoggedInClientActiveContracts(user);
    }

    @GetMapping("/count-expired-loggedin-client")
    @PreAuthorize("hasRole('CLIENT')")
    public int getTotalNumberOfExpiredContracts(@AuthenticationPrincipal UserDetailsImpl user){
        return contractService.countLoggedInClientExpiredContracts(user);
    }
}
