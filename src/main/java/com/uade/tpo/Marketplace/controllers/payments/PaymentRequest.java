package com.uade.tpo.Marketplace.controllers.payments;

import lombok.Data;

@Data
public class PaymentRequest {
    private double amount;
    private Long orderId;
    private Long paymentMethodId;
    private int dni;
}