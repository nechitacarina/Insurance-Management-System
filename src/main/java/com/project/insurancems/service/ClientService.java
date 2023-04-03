package com.project.insurancems.service;

import com.project.insurancems.entity.*;
import com.project.insurancems.payload.request.CreateClientRequest;
import com.project.insurancems.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    PolicyRepository policyRepository;

    PasswordEncoder passwordEncoder;

    public int getTotalNumberOfClients(){
        return clientRepository.countClients();
    }

    public int getTotalNumberOfClientsWithVehicleInsurance(){
        return clientRepository.countNumberOfClientsWithVehicleInsurance();
    }

    public int getTotalNumberOfClientsWithPropertyInsurance(){
        return clientRepository.countNumberOfClientsWithPropertyInsurance();
    }

    public int getTotalNumberOfClientsWithLifeInsurance(){
        return clientRepository.countNumberOfClientsWithLifeInsurance();
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Client getClientById(int id){
        return clientRepository.getClientById(id);
    }

    public Client editClientInfo(int id, Client client){
        Client updateClient = clientRepository.getClientById(id);
        updateClient.setCNP(client.getCNP());
        updateClient.setDob(client.getDob());
        updateClient.setGender(client.getGender());
        updateClient.setIncome(client.getIncome());
        updateClient.setPhoneNumber(client.getPhoneNumber());
        updateClient.getUser().setEmail(client.getUser().getEmail());
        updateClient.getUser().setFirstName(client.getUser().getFirstName());
        updateClient.getUser().setLastName(client.getUser().getLastName());
        clientRepository.save(updateClient);
        return updateClient;
    }

    public Client editClientPassword(int id, Client client){
        Client updateClient = clientRepository.getClientById(id);
        this.passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = this.passwordEncoder.encode(client.getUser().getPassword());
        updateClient.getUser().setPassword(encodedPassword);
        clientRepository.save(client);
        return updateClient;
    }

    public void deleteClient(int id){
        Client client = clientRepository.getClientById(id);
        clientRepository.delete(client);
    }

    public void createClient(CreateClientRequest clientRequest){
        System.out.println(clientRequest);
        User user = new User();
        user.setFirstName(clientRequest.getUser().getFirstName());
        user.setLastName(clientRequest.getUser().getLastName());
        List<Role> roleClient = roleRepository.findAll();
        user.setRole(roleClient.get(1));
        user.setEmail(clientRequest.getUser().getEmail());
        this.passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = this.passwordEncoder.encode(clientRequest.getUser().getPassword());
        user.setPassword(encodedPassword);
        Client client = new Client();
        client.setCNP(clientRequest.getClient().getCNP());
        client.setDob(clientRequest.getClient().getDob());
        client.setGender(clientRequest.getClient().getGender());
        client.setIncome(clientRequest.getClient().getIncome());
        client.setPhoneNumber(clientRequest.getClient().getPhoneNumber());
        client.setUser(user);
        clientRepository.save(client);
        Contract contract = new Contract();
        contract.setClient(client);
        contract.setStatus(clientRequest.getContract().getStatus());
        contract.setPrice(clientRequest.getContract().getPrice());
        int idPolicy = clientRequest.getSelectedPolicy();
        Optional<Policy> policy = policyRepository.findById(idPolicy);
        contract.setPolicy(policy.get());
        contract.setExpirationDate(clientRequest.getContract().getExpirationDate());
        contract.setEffectiveDate(clientRequest.getContract().getEffectiveDate());
        contract.setMaxClaimAmount(clientRequest.getContract().getMaxClaimAmount());
        contract.setStatus(ContractStatus.ACTIVE);
        contractRepository.save(contract);
    }
}
