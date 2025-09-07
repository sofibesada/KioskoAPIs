package com.uade.tpo.Marketplace.controllers.payments;

import com.uade.tpo.Marketplace.entity.PaymentMethod;

import lombok.Data;

@Data
public class PaymentRequest {
    private double amount;
    private Long orderId;
    private PaymentMethod paymentMethod;
    private int dni;
}