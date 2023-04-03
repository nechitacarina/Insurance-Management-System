package com.project.insurancems.repository;

import com.project.insurancems.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT COUNT(*) FROM Category")
    public int countCategory();

    @Query("SELECT DISTINCT category FROM Category category, Contract contract, Policy policy, User user, Client client " +
            "WHERE contract.client.id = client.id AND client.user.id = user.id " +
            "AND contract.policy.id = policy.id " +
            "AND policy.category.id = category.id " +
            "AND client.id = :id")
    public List<Category> findInsuranceCategoriesByClientPolicy(@Param("id") int id);
}
