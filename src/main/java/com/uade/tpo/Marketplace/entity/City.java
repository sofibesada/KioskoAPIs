package com.uade.tpo.Marketplace.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    private String id; // viene de la API Georef
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "province", nullable = false)
    private Province province;

    @OneToMany(mappedBy = "city")
    private List<Address> addresses;
}
