package com.project.insurancems.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coverages")
@Data
public class Coverage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coverage")
    private int id;

    @Column(name = "covered_risk", nullable = false, length = 50)
    private String coveredRisk;

    @ManyToMany
    @JoinTable(name = "coverages_policies",
            joinColumns = @JoinColumn(name = "id_coverage"),
            inverseJoinColumns = @JoinColumn(name = "id_policy")
    )
    private List<Policy> policies;
}
