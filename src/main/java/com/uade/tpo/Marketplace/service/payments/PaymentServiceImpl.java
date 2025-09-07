package com.uade.tpo.Marketplace.service.payments;

import com.uade.tpo.Marketplace.entity.Order;
import com.uade.tpo.Marketplace.entity.Payment;
import com.uade.tpo.Marketplace.entity.PaymentMethod;
import com.uade.tpo.Marketplace.repository.orderdetails.OrderDetailRepository;
import com.uade.tpo.Marketplace.repository.orders.OrderRepository;
import com.uade.tpo.Marketplace.repository.payments.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public Payment createPayment(double amount, Long orderId, PaymentMethod paymentMethod, int dni) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada con id: " + orderId));


        Payment payment = new Payment();
        payment.setCreated_at(new Timestamp(System.currentTimeMillis()));
        payment.setAmount(order.getTotalAmount());
        payment.setOrder(order);
        payment.setMethod(paymentMethod);
        payment.setDni(dni);

        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(Long id, double amount, Long orderId, PaymentMethod paymentMethod, int dni) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pago no encontrado con id: " + id));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada con id: " + orderId));


        payment.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        payment.setAmount(order.getTotalAmount());
        payment.setOrder(order);
        payment.setMethod(paymentMethod);
        payment.setDni(dni);

        return paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pago no encontrado con id: " + id));

        payment.setDelete_at(new Timestamp(System.currentTimeMillis()));
        paymentRepository.save(payment);
    }
}
