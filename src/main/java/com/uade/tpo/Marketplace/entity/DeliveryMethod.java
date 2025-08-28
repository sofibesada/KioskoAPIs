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

public class DeliveryMethod {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name; //si es a domicilio/local..
    @Column
    private String description;
    @Column
    private float price;

    @OneToMany (mappedBy = "deliveryMethod")
    private List<Order> order;

}
