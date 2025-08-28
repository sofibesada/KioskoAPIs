package com.uade.tpo.Marketplace.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @OneToMany(mappedBy = "address")
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;


}
