package com.project.insurancems.repository;

import com.project.insurancems.entity.Category;
import com.project.insurancems.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

    @Query("SELECT COUNT(p.id) FROM Policy p, Category c " +
            "WHERE p.category.id = c.id " +
            "AND c.title = 'Vehicle Insurance'")
    public int countVehiclePolicies();

    @Query("SELECT COUNT(p.id) FROM Policy p, Category c " +
            "WHERE p.category.id = c.id " +
            "AND c.title = 'Property Insurance'")
    public int countPropertyPolicies();

    @Query("SELECT COUNT(p.id) FROM Policy p, Category c " +
            "WHERE p.category.id = c.id " +
            "AND c.title = 'Life Insurance'")
    public int countLifePolicies();

    public List<Policy> getPoliciesByCategoryId(int id);

    @Query("SELECT pol FROM Policy pol, Contract contract, Client cl, User user, Category category " +
            "WHERE contract.policy.id = pol.id " +
            "AND contract.client.id = cl.id AND cl.user.id = user.id " +
            "AND contract.expirationDate >= CURRENT_DATE " +
            "AND cl.id = :idClient " +
            "GROUP BY pol, pol.category.id " +
            "HAVING pol.category.id = :idCategory")
    public List<Policy> getClientPolicyByCategory(@Param("idClient") int idClient, @Param("idCategory") int idCategory);
}
