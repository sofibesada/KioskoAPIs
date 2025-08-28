package com.uade.tpo.Marketplace.controllers.categories.orders;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uade.tpo.Marketplace.entity.Order;
import com.uade.tpo.Marketplace.service.orders.OrderService;

@RestController
@RequestMapping("/orders")

public class OrderController {

    @Autowired private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<Order> getOrderByNumber(@PathVariable int number) {
        return orderService.getOrderByNumber(number)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {
        Order order = orderService.createOrder(
                request.getUserId(),
                request.getDeliveryMethodId(),
                request.getOrderStateId(),
                request.getTotalAmount()
        );
        return ResponseEntity.created(URI.create("/orders/" + order.getId())).body(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody OrderRequest request) {
        Order updated = orderService.updateOrder(
                id,
                request.getUserId(),
                request.getDeliveryMethodId(),
                request.getOrderStateId(),
                request.getTotalAmount()
        );
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
