package com.uade.tpo.Marketplace.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //un usuario tiene muchos favoritos
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //un producto esta en los favoritos de muchos usuarios

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}