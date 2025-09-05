package com.uade.tpo.Marketplace.repository.reviiew;

import com.uade.tpo.Marketplace.entity.Reviiew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Reviiew, Long> {
    List<Reviiew> findByProductId(Long productId);

    @Query("SELECT AVG(r.rating) FROM Reviiew r WHERE r.product.id = :productId")
    Double getAverageRatingByProductId(Long productId);
}
