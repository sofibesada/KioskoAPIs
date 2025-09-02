package com.uade.tpo.Marketplace.controllers.categories.auth;



import java.time.LocalDateTime;

import com.uade.tpo.Marketplace.entity.Genders;
import com.uade.tpo.Marketplace.entity.UserType;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class RegisterRequest {
    private String firstname;   // se mapea a User.name
    private String lastname;    // se mapea a User.surname
    private String email;
    private String password;
    private UserType userType;    // "Cliente", "Admin", etc. (String)
    private Genders gender;
    
    
}

