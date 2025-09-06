package com.uade.tpo.Marketplace.controllers.deliveries;

import lombok.Data;

@Data

public class DeliveryMethodRequest {
    private String name;
    private String description;
    private float price;
}
