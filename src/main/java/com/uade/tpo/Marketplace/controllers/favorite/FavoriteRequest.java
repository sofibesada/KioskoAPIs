package com.uade.tpo.Marketplace.controllers.favorite;

import lombok.Data;

@Data
public class FavoriteRequest {
    private Long userId;
    private Long productId;
}
