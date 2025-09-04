package com.uade.tpo.Marketplace.controllers.revieww;


import lombok.Data;

@Data
public class ReviewRequest {
    private int rating;
    private String comment;
    private Long userId;
    private Long productId;
}