package com.project.insurancems.repository;

import com.project.insurancems.entity.Client;
import com.project.insurancems.entity.Contract;
import com.project.insurancems.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {

    @Query("SELECT COUNT(*) FROM Contract c WHERE c.status = 'ACTIVE'")
    public int countActiveContracts();

    @Query("SELECT COUNT(*) FROM Contract c WHERE c.status = 'EXPIRED'")
    public int countExpiredContracts();

    public List<Contract> findContractsByClient_Id(int id);

    public Contract getContractById(int id);

    public int countContractByClient_Id(int id);

    @Query("SELECT COUNT(c.id) FROM Contract c WHERE current_date <= c.expirationDate " +
            "AND c.client.id = (SELECT cl.id FROM Client cl WHERE cl.id = :id)")
    public int countActiveContractByClient_Id(@Param("id") int id);

    @Query("SELECT COUNT(*) FROM Contract c WHERE current_date > c.expirationDate " +
            "AND c.client.id = (SELECT cl.id FROM Client cl WHERE cl.id = :id)")
    public int countExpiredContractByClient_Id(@Param("id") int id);
}
