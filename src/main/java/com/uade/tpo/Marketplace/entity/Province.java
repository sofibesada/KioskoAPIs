package com.uade.tpo.Marketplace.entity;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province {
    @Id
    private String id; 
    private String nombre;

    @OneToMany(mappedBy = "province")
    private List<Address> addresses;
    
}
