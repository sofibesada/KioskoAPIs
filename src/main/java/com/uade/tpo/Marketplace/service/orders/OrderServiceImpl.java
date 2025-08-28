package com.uade.tpo.Marketplace.service.orders;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.Marketplace.entity.*;
import com.uade.tpo.Marketplace.repository.orders.OrderRepository;
import com.uade.tpo.Marketplace.repository.users.UserRepository;
import com.uade.tpo.Marketplace.repository.deliveries.DeliveryMethodRepository;
import com.uade.tpo.Marketplace.repository.orderstates.OrderStateRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired private OrderRepository orderRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private DeliveryMethodRepository deliveryMethodRepository;
    @Autowired private OrderStateRepository orderStateRepository;

    @Override
    public List<Order> getOrders() {
        return orderRepository.findByDeleteAtIsNull();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<Order> getOrderByNumber(int number) {
        return Optional.ofNullable(orderRepository.findByNumber(number));
    }

    @Override
    public Order createOrder(Long userId, Long deliveryMethodId, Long orderStateId, Long totalAmount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + userId));
        DeliveryMethod deliveryMethod = deliveryMethodRepository.findById(deliveryMethodId)
                .orElseThrow(() -> new IllegalArgumentException("Método de entrega no encontrado con id: " + deliveryMethodId));
        OrderState orderState = orderStateRepository.findById(orderStateId)
                .orElseThrow(() -> new IllegalArgumentException("Estado de orden no encontrado con id: " + orderStateId));

        Order order = new Order();
        order.setUser(user);
        order.setDeliveryMethod(deliveryMethod);
        order.setOrderState(orderState);
        order.setTotal_amount(totalAmount);
        order.setCreated_at(Timestamp.from(Instant.now()));

        return orderRepository.save(order); // acá Hibernate persiste y luego @PostPersist setea number
    }

    @Override
    public Order updateOrder(Long id, Long userId, Long deliveryMethodId, Long orderStateId, Long totalAmount) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada con id: " + id));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + userId));
        DeliveryMethod deliveryMethod = deliveryMethodRepository.findById(deliveryMethodId)
                .orElseThrow(() -> new IllegalArgumentException("Método de entrega no encontrado con id: " + deliveryMethodId));
        OrderState orderState = orderStateRepository.findById(orderStateId)
                .orElseThrow(() -> new IllegalArgumentException("Estado de orden no encontrado con id: " + orderStateId));

        order.setUser(user);
        order.setDeliveryMethod(deliveryMethod);
        order.setOrderState(orderState);
        order.setTotal_amount(totalAmount);
        order.setUpdated_at(Timestamp.from(Instant.now()));

        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada con id: " + id));
        order.setDeleteAt(Timestamp.from(Instant.now()));
        orderRepository.save(order);
    }
}
