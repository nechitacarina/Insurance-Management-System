package com.project.insurancems.repository;

import com.project.insurancems.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query("SELECT COUNT(*) FROM Client")
    public int countClients();

    @Query("SELECT COUNT(client) FROM Client client " +
            "WHERE client.id IN (SELECT contract.client.id FROM Contract contract " +
            "WHERE contract.expirationDate >= CURRENT_DATE " +
            "AND contract.policy.id IN (SELECT policy.id FROM Policy policy " +
            "WHERE policy.category.id IN (SELECT category.id FROM Category category " +
            "WHERE category.id = 1)))")
    public int countNumberOfClientsWithVehicleInsurance();

    @Query("SELECT COUNT(client) FROM Client client " +
            "WHERE client.id IN (SELECT contract.client.id FROM Contract contract " +
            "WHERE contract.expirationDate >= CURRENT_DATE " +
            "AND contract.policy.id IN (SELECT policy.id FROM Policy policy " +
            "WHERE policy.category.id IN (SELECT category.id FROM Category category " +
            "WHERE category.id = 2)))")
    public int countNumberOfClientsWithPropertyInsurance();

    @Query("SELECT COUNT(client) FROM Client client " +
            "WHERE client.id IN (SELECT contract.client.id FROM Contract contract " +
            "WHERE contract.expirationDate >= CURRENT_DATE " +
            "AND contract.policy.id IN (SELECT policy.id FROM Policy policy " +
            "WHERE policy.category.id IN (SELECT category.id FROM Category category " +
            "WHERE category.id = 3)))")
    public int countNumberOfClientsWithLifeInsurance();

    public Client getClientById(int id);
    public Client getClientByUserId(int id);

    public Optional<Client> findClientByCNP(String cnp);
}
