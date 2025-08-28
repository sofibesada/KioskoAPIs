package com.uade.tpo.Marketplace.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data

@Entity
public class Payment {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Timestamp created_at;
    @Column
    private Timestamp updated_at;
    @Column
    private Timestamp delete_at; 

    @Column
    private double amount;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false, unique = true) // garantiza 1–1
    private Order order;

    @OneToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;
    
}
