package com.uade.tpo.Marketplace.service.payments;

import com.uade.tpo.Marketplace.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<Payment> getPayments();
    Optional<Payment> getPaymentById(Long id);
    Payment createPayment(double amount, Long orderId, Long paymentMethodId, int dni);
    Payment updatePayment(Long id, double amount, Long orderId, Long paymentMethodId, int dni);
    void deletePayment(Long id);
}
