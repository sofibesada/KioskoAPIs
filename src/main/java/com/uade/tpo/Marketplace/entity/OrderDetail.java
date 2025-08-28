package com.uade.tpo.Marketplace.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data

@Entity
public class OrderDetail {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private int quantity;
    @Column
    private float subtotal;
    
    @ManyToOne
    @JoinColumn (name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn (name = "product_id", nullable = false)
    private Product product;


}
