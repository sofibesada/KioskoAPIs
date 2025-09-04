package com.uade.tpo.Marketplace.controllers.revieww;



import com.uade.tpo.Marketplace.entity.Reviiew;
import com.uade.tpo.Marketplace.service.reviiew.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<Reviiew> getReviews() {
        return reviewService.getReviews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reviiew> getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/product/{productId}")
    public List<Reviiew> getReviewsByProduct(@PathVariable Long productId) {
        return reviewService.getReviewsByProduct(productId);
    }

    @GetMapping("/product/{productId}/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long productId) {
        Double avg = reviewService.getAverageRating(productId);
        return ResponseEntity.ok(avg != null ? avg : 0.0);
    }

    @PostMapping
    public ResponseEntity<Reviiew> createReview(@RequestBody ReviewRequest request) {
        Reviiew review = reviewService.createReview(
                request.getRating(),
                request.getComment(),
                request.getUserId(),
                request.getProductId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(review);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
