package com.uade.tpo.Marketplace.controllers.categories.users;

import java.time.LocalDate;
import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String surname;
    private int dni;
    private String email;
    private String password;
    private String phone;
    private LocalDate birthDate;
    private Long addressId;
    private Long genderId;
    private Long userTypeId;
}
