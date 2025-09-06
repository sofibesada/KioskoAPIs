package com.uade.tpo.Marketplace.controllers.typereceipts;

import lombok.Data;

@Data

public class TypeReceiptRequest {
    private int number;
    private String receipt; //si es A, B, C, Ticket
}
