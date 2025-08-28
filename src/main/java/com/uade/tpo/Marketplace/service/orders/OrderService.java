package com.uade.tpo.Marketplace.service.orders;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.Marketplace.entity.Order;

public interface OrderService {
    List<Order> getOrders();
    Optional<Order> getOrderById(Long id);
    Optional<Order> getOrderByNumber(int number);
    Order createOrder(Long userId, Long deliveryMethodId, Long orderStateId, Long totalAmount);
    Order updateOrder(Long id, Long userId, Long deliveryMethodId, Long orderStateId, Long totalAmount);
    void deleteOrder(Long id);
}

