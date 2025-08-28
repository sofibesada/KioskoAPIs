package com.uade.tpo.Marketplace.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data


public class Invoice {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column 
    private Timestamp createdAt;
    @Column
    private float total_amount;
    @Column
    private int dni_user;
    @Column
    private int receiptNumber;
    @Column
    private String type;

    @OneToOne
    @JoinColumn (name = "order_id", nullable = false, unique = true)
    private Order order;

    @ManyToOne
    @JoinColumn (name = "type_receipt_id", nullable = false)
    private TypeReceipt typeReceipt;

}
