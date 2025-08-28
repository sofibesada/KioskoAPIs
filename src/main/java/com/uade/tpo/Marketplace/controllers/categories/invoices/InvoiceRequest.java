package com.uade.tpo.Marketplace.controllers.categories.invoices;

import lombok.Data;

@Data

public class InvoiceRequest {
    private float totalAmount;
    private int dniUser;
    private int receiptNumber;
    private String type;
    private Long orderId;
    private Long typeReceiptId;
}

