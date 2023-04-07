package com.project.insurancems.service;

import com.project.insurancems.entity.*;
import com.project.insurancems.payload.request.CreateClientRequest;
import com.project.insurancems.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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

    public Client editClientInfo(int id, Client client) throws Exception {
        String cnp = client.getCNP();
        LocalDate dob = client.getDob();
        String phoneNumber = client.getPhoneNumber();
        Period period = dob.until(LocalDate.now());
        int yearsBetween = period.getYears();

        Client updateClient = clientRepository.getClientById(id);
        if(cnp.length() == 13 && cnp.matches("[0-9]+")){
            updateClient.setCNP(cnp);
        }else {
            throw new Exception("Invalid cnp!");
        }
        if(dob.isBefore(LocalDate.now()) && yearsBetween >= 18){
            updateClient.setDob(dob);
        }else {
            throw new Exception("Invalid date of birth!");
        }
        updateClient.setGender(client.getGender());
        updateClient.setIncome(client.getIncome());
        if(phoneNumber.matches("[0-9]+") && phoneNumber.length() == 10){
            updateClient.setPhoneNumber(phoneNumber);
        }else {
            throw new Exception("Invalid phone number!");
        }
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

    public void createClient(CreateClientRequest clientRequest) throws Exception {

        String cnp = clientRequest.getClient().getCNP();
        LocalDate dob = clientRequest.getClient().getDob();
        String phoneNumber = clientRequest.getClient().getPhoneNumber();
        LocalDate expirationDate = clientRequest.getContract().getExpirationDate();
        LocalDate effectiveDate = clientRequest.getContract().getEffectiveDate();
        Period period = dob.until(LocalDate.now());
        int yearsBetween = period.getYears();

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
        if(cnp.length() == 13 && cnp.matches("[0-9]+")){
            client.setCNP(cnp);
        }else {
            throw new Exception("Invalid CNP");
        }
        if(dob.isBefore(LocalDate.now()) && yearsBetween >= 18){
            client.setDob(dob);
        }else {
            throw new Exception("Invalid Date of Birth");
        }
        client.setGender(clientRequest.getClient().getGender());
        client.setIncome(clientRequest.getClient().getIncome());
        if(phoneNumber.matches("[0-9]+") && phoneNumber.length() == 10){
            client.setPhoneNumber(phoneNumber);
        }else {
            throw new Exception("Invalid phone number!");
        }
        client.setUser(user);
        Optional<Client> checkClient = clientRepository.findClientByCNP(cnp);
        if(checkClient.isPresent()){
            throw new Exception("A client with this CNP already exists!");
        }else {
            clientRepository.save(client);
        }
        Contract contract = new Contract();
        contract.setClient(client);
        contract.setStatus(ContractStatus.ACTIVE);
        contract.setPrice(clientRequest.getContract().getPrice());
        int idPolicy = clientRequest.getSelectedPolicy();
        Optional<Policy> policy = policyRepository.findById(idPolicy);
        if(policy.isPresent()){
            contract.setPolicy(policy.get());
        }else {
            throw new Exception("The policy does not exists!");
        }
        if(expirationDate.isAfter(effectiveDate)){
            contract.setExpirationDate(expirationDate);
        }else {
            throw new Exception("The Expiration Date cannot be before Effective date!");
        }
        if(effectiveDate.isBefore(expirationDate)){
            contract.setEffectiveDate(effectiveDate);
        }else {
            throw new Exception("The Effective Date cannot be after Expiration date!");
        }
        contract.setMaxClaimAmount(clientRequest.getContract().getMaxClaimAmount());
        contract.setStatus(ContractStatus.ACTIVE);
        contractRepository.save(contract);
    }
}
