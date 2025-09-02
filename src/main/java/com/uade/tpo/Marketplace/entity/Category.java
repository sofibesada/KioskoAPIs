package com.uade.tpo.Marketplace.entity;

import lombok.Data;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Data //define automaticamente todos los getters y setters de nuestra aplicacion
//gracias al framework que estamos usando, podemos usar esto.
//@Builder //nos permite crear objetos dentro del repositorio
@Entity //entidad que va a tener que persistir en la base de datos, le digo agarra tal objeto, mapealo y transformalo en tabla en mi bd
public class Category { //tabla
    
    public Category(){}

    public Category (String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Id //me define mi PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //q se autogenere un dato
    private Long id; //columna1

    @Column
    private String name;
    @Column
    private String description; //columna2

    @OneToMany (mappedBy = "category")
    private List<Product> products;

}
