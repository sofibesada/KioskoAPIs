package com.uade.tpo.Marketplace.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity

public class User  { 

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private int dni;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String phone;
    @Column
    private LocalDate birthDate;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "delete_at")
    private LocalDateTime deleteAt;
    
  

    
    @OneToMany(mappedBy = "user") //user va a ser FK de tabla de orders
    private List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private Genders gender;

    @ManyToOne
    @JoinColumn(name="userType_id",nullable = false)
    private UserType userType;

   

}



