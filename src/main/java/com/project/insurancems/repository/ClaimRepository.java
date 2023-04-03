package com.project.insurancems.repository;

import com.project.insurancems.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Integer> {

    @Query("SELECT COUNT(c) FROM Claim c WHERE c.status = 'PENDING'")
    public int getTotalNumberOfPendingClaims();
    public List<Claim> findClaimByClient_Id(int id);

    @Query("SELECT c FROM Claim c WHERE c.client.user.id = :id")
    public List<Claim> findAuthenticatedUserClaims(@Param("id") int id);

    public int countClaimByClient_Id(int id);

    @Query("SELECT COUNT(c.id) FROM Claim c WHERE c.status = 'ACCEPTED' " +
            "AND c.client.id = (SELECT cl.id FROM Client cl WHERE cl.id = :id)")
    public int countAcceptedClaimsByClient_Id(int id);

    @Query("SELECT COUNT(c.id) FROM Claim c WHERE c.status = 'REJECTED' " +
            "AND c.client.id = (SELECT cl.id FROM Client cl WHERE cl.id = :id)")
    public int countRejectedClaimsByClient_Id(int id);
}
