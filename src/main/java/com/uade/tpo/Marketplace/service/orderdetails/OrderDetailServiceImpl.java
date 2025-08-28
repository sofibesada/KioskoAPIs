package com.uade.tpo.Marketplace.service.orderdetails;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.Marketplace.entity.Order;

import com.uade.tpo.Marketplace.entity.OrderDetail;
import com.uade.tpo.Marketplace.entity.Product;
import com.uade.tpo.Marketplace.repository.orderdetails.OrderDetailRepository;
import com.uade.tpo.Marketplace.repository.orders.OrderRepository;
import com.uade.tpo.Marketplace.repository.products.ProductRepository;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<OrderDetail> getOrderDetails() {
        return orderDetailRepository.findAll();
    }

    @Override
    public Optional<OrderDetail> getOrderDetailById(Long id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    @Override
    public OrderDetail createOrderDetail(int quantity, Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada con id: " + orderId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con id: " + productId));

        OrderDetail detail = new OrderDetail();
        detail.setOrder(order);
        detail.setProduct(product);
        detail.setQuantity(quantity);
        detail.setSubtotal(quantity * product.getPrice());

        return orderDetailRepository.save(detail);
    }

    @Override
    public OrderDetail updateOrderDetail(Long id, int quantity) {
        OrderDetail detail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Detalle no encontrado con id: " + id));

        detail.setQuantity(quantity);
        detail.setSubtotal(quantity * detail.getProduct().getPrice());

        return orderDetailRepository.save(detail);
    }

    @Override
    public void deleteOrderDetail(Long id) {
        orderDetailRepository.deleteById(id);
    }
}