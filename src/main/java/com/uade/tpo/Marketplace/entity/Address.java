package com.uade.tpo.Marketplace.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data

public class Address {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String street;
    @Column
    private int number;
    @Column
    private int floor;
    @Column
    private int department;
    @Column
    private int codigoPostal;
    @Column
    private String city;
    @Column(nullable = false)
    private String country = "Argentina";
    @Column
    private String province;

    @OneToMany(mappedBy = "address")
    private List<User> users;


}
