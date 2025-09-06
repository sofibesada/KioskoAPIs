package com.uade.tpo.Marketplace.controllers.address;

import lombok.Data;

@Data

public class AddressRequest {
    private String street;
    private int number;
    private int floor;
    private int department;
    private int codigoPostal;
    private String city;
    private String provincia;

}