package com.uade.tpo.Marketplace.controllers.orders;




import lombok.Data;

@Data

public class OrderRequest {
    private Long userId;
    private Long deliveryMethodId;
}
