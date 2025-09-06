package com.uade.tpo.Marketplace.controllers.orderdetails;

import lombok.Data;

@Data

public class OrderDetailRequest {
    private int quantity;
    private Long orderId;
    private Long productId;
}
