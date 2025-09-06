package com.uade.tpo.Marketplace.controllers.paymentmethods;

import lombok.Data;

@Data
public class PaymentMethodRequest {
    private String type;
    private String details;
}
