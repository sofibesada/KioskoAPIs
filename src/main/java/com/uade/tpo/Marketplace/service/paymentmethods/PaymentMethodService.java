package com.uade.tpo.Marketplace.service.paymentmethods;

import com.uade.tpo.Marketplace.entity.PaymentMethod;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodService {
    List<PaymentMethod> getPaymentMethods();
    Optional<PaymentMethod> getPaymentMethodById(Long id);
    PaymentMethod createPaymentMethod(String type, String details);
    PaymentMethod updatePaymentMethod(Long id, String type, String details);
    void deletePaymentMethod(Long id);
}
