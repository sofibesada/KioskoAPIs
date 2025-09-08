package com.uade.tpo.Marketplace.repository.payments;

import com.uade.tpo.Marketplace.entity.Payment;
import com.uade.tpo.Marketplace.entity.PaymentMethod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByOrderId(Long orderId);
    List<Payment> findByAmountGreaterThan(double amount);
    List<Payment> findByMethod(PaymentMethod method);

}