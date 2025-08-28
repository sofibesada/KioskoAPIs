package com.uade.tpo.Marketplace.repository.paymentmethods;

import com.uade.tpo.Marketplace.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    List<PaymentMethod> findByType(String type);

    List<PaymentMethod> findByDetails(String details);
}

