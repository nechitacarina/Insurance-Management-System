package com.project.insurancems.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "policies")
@Data
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_policy")
    private int id;

    @Column(nullable = false, length = 50)
    private String title;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
}
