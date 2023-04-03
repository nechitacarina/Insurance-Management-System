package com.project.insurancems.repository;

import com.project.insurancems.entity.Coverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoverageRepository extends JpaRepository<Coverage, Integer> {

    @Query("SELECT cv FROM Coverage cv JOIN cv.policies pol WHERE pol.id = :id")
    public List<Coverage> getCoverageByPolicy(@Param("id") int id);

}
