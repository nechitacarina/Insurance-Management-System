package com.project.insurancems.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private int id;

    @Column(nullable = false, length = 45)
    private String title;
}
