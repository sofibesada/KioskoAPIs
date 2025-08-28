package com.uade.tpo.Marketplace.repository.payments;

import com.uade.tpo.Marketplace.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Buscar todos los pagos de un pedido
    List<Payment> findByOrderId(Long orderId);

    // Buscar pagos por monto mayor a X
    List<Payment> findByAmountGreaterThan(double amount);

    // Buscar pagos por método
    List<Payment> findByPaymentMethodId(Long paymentMethodId);
}