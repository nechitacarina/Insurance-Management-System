package com.project.insurancems.service;

import com.project.insurancems.entity.Client;
import com.project.insurancems.entity.Contract;
import com.project.insurancems.entity.ContractStatus;
import com.project.insurancems.entity.Policy;
import com.project.insurancems.payload.request.CreateContractRequest;
import com.project.insurancems.repository.ClientRepository;
import com.project.insurancems.repository.ContractRepository;
import com.project.insurancems.repository.PolicyRepository;
import com.project.insurancems.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private ClientRepository clientRepository;

    public int getTotalNumberOfActiveContracts(){
        return contractRepository.countActiveContracts();
    }

    public int getTotalNumberOfExpiredContracts(){
        return contractRepository.countExpiredContracts();
    }

    public List<Contract> getContractsByClientId(int id){
        return contractRepository.findContractsByClient_Id(id);
    }

    public Contract createContract(CreateContractRequest contractRequest){
        Contract contract = new Contract();
        int idPolicy = contractRequest.getIdPolicy();
        Optional<Policy> policy = policyRepository.findById(idPolicy);
        contract.setPolicy(policy.get());
        contract.setEffectiveDate(contractRequest.getEffectiveDate());
        contract.setExpirationDate(contractRequest.getExpirationDate());
        contract.setStatus(ContractStatus.ACTIVE);
        contract.setPrice(contractRequest.getPrice());
        contract.setMaxClaimAmount(contractRequest.getMaxClaimAmount());
        int idClient = contractRequest.getIdClient();
        Optional<Client> client = clientRepository.findById(idClient);
        contract.setClient(client.get());
        return contractRepository.save(contract);
    }

    public Contract updateContractAvability(int id, Contract contract){
        Contract updateContract = contractRepository.getContractById(id);
        updateContract.setEffectiveDate(contract.getEffectiveDate());
        updateContract.setExpirationDate(contract.getExpirationDate());
        return contractRepository.save(updateContract);
    }

    public Contract getContractById(int id){
        return contractRepository.getContractById(id);
    }

    public int countLoggedInClientContracts(UserDetailsImpl user){
        int idUser = user.getId();
        Client client = clientRepository.getClientByUserId(idUser);
        int idClient = client.getId();
        return contractRepository.countContractByClient_Id(idClient);
    }

    public int countLoggedInClientActiveContracts(UserDetailsImpl user){
        int idUser = user.getId();
        Client client = clientRepository.getClientByUserId(idUser);
        int idClient = client.getId();
        return contractRepository.countActiveContractByClient_Id(idClient);
    }

    public int countLoggedInClientExpiredContracts(UserDetailsImpl user){
        int idUser = user.getId();
        Client client = clientRepository.getClientByUserId(idUser);
        int idClient = client.getId();
        return contractRepository.countExpiredContractByClient_Id(idClient);
    }
}
