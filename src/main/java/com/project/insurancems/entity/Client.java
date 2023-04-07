package com.project.insurancems.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clients")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private int id;

    @Column(name = "cnp", nullable = false, length = 13)
    private String CNP;

    @Column(name = "phone_number", nullable = false, length = 10)
    private String phoneNumber;

    @Column(name = "date_of_birth", nullable = false)
    @DateTimeFormat
    private LocalDate dob;

    @Column(nullable = false)
    private char gender;

    @Column(nullable = false)
    private double income;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;
}
