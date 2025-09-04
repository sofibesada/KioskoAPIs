package com.uade.tpo.Marketplace.service.reviiew;


import com.uade.tpo.Marketplace.entity.Product;
import com.uade.tpo.Marketplace.entity.Reviiew;
import com.uade.tpo.Marketplace.entity.User;
import com.uade.tpo.Marketplace.repository.products.ProductRepository;
import com.uade.tpo.Marketplace.repository.reviiew.ReviewRepository;
import com.uade.tpo.Marketplace.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Reviiew> getReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Reviiew> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Reviiew> getReviewsByProduct(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    @Override
    public Double getAverageRating(Long productId) {
        return reviewRepository.getAverageRatingByProductId(productId);
    }

    @Override
    public Reviiew createReview(int rating, String comment, Long userId, Long productId) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("El rating debe estar entre 1 y 5");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con id: " + productId));

        Reviiew review = new Reviiew();
        review.setRating(rating);
        review.setComment(comment);
        review.setUser(user);
        review.setProduct(product);

        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}

