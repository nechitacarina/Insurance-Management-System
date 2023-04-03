package com.project.insurancems.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private int id;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ERole title;
}
