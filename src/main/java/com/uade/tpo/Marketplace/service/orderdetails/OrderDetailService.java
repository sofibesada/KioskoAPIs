package com.uade.tpo.Marketplace.service.orderdetails;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.Marketplace.entity.OrderDetail;

public interface OrderDetailService {
    List<OrderDetail> getOrderDetails();
    Optional<OrderDetail> getOrderDetailById(Long id);
    List<OrderDetail> getOrderDetailsByOrderId(Long orderId);
    OrderDetail createOrderDetail(int quantity, Long orderId, Long productId);
    OrderDetail updateOrderDetail(Long id, int quantity);
    void deleteOrderDetail(Long id);
}
