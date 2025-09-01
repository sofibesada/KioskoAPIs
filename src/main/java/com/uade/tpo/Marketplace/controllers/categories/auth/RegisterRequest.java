package com.uade.tpo.Marketplace.controllers.categories.auth;

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
    private String userType;    // "Cliente", "Admin", etc. (String)
    private Long genderId;
}

