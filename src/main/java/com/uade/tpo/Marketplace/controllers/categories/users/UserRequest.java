package com.uade.tpo.Marketplace.controllers.categories.users;

import java.time.LocalDate;

import com.uade.tpo.Marketplace.entity.Genders;
import com.uade.tpo.Marketplace.entity.UserType;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private LocalDate birthDate;
    private Long addressId;
    private Genders gender;
    private UserType usertype;
}
