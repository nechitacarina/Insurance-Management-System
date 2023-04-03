package com.project.insurancems.controller;

import com.project.insurancems.entity.Client;
import com.project.insurancems.payload.request.CreateClientRequest;
import com.project.insurancems.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @GetMapping("/count")
    @PreAuthorize("hasRole('AGENT')")
    public int getTotalNumberOfClients() {
        return clientService.getTotalNumberOfClients();
    }

    @GetMapping("/count-vehicle-insurance")
    @PreAuthorize("hasRole('AGENT')")
    public int getTotalNumberOfClientsWithVehicleInsurance() {
        return clientService.getTotalNumberOfClientsWithVehicleInsurance();
    }

    @GetMapping("/count-property-insurance")
    @PreAuthorize("hasRole('AGENT')")
    public int getTotalNumberOfClientsWithPropertyInsurance() {
        return clientService.getTotalNumberOfClientsWithPropertyInsurance();
    }

    @GetMapping("/count-life-insurance")
    @PreAuthorize("hasRole('AGENT')")
    public int getTotalNumberOfClientsWithLifeInsurance() {
        return clientService.getTotalNumberOfClientsWithLifeInsurance();
    }

    @GetMapping
    @PreAuthorize("hasRole('AGENT')")
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public Client getClientById(@PathVariable int id){
        return clientService.getClientById(id);
    }

    @PutMapping("/info/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public Client editClientInfo(@PathVariable int id, @RequestBody Client client){
        return clientService.editClientInfo(id, client);
    }

    @PutMapping("/password/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public Client editClientPassword(@PathVariable int id, @RequestBody Client client){
        return clientService.editClientPassword(id, client);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public void deleteClient(@PathVariable int id){
        clientService.deleteClient(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('AGENT')")
    public void createClient(@RequestBody CreateClientRequest clientRequest){
        clientService.createClient(clientRequest);
    }
}
