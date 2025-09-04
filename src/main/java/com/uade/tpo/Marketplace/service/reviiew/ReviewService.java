package com.uade.tpo.Marketplace.service.reviiew;

import com.uade.tpo.Marketplace.entity.Reviiew;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Reviiew> getReviews();
    Optional<Reviiew> getReviewById(Long id);
    List<Reviiew> getReviewsByProduct(Long productId);
    Double getAverageRating(Long productId);
    Reviiew createReview(int rating, String comment, Long userId, Long productId);
    void deleteReview(Long id);
}
