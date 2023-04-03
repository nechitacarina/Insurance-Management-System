package com.project.insurancems.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 60)
    private String email;

    @Column(nullable = false, length = 70)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;
}
