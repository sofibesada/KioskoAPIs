package com.uade.tpo.Marketplace.controllers.orderstates;
import com.uade.tpo.Marketplace.entity.OrderState;
import com.uade.tpo.Marketplace.service.orderstates.OrderStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/order-states")
public class OrderStateController {

    @Autowired
    private OrderStateService orderStateService;

    @GetMapping
    public ResponseEntity<List<OrderState>> getOrderStates() {
        return ResponseEntity.ok(orderStateService.getOrderStates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderState> getOrderStateById(@PathVariable Long id) {
        return orderStateService.getOrderStateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderState> createOrderState(@RequestBody OrderStateRequest request) {
        OrderState state = orderStateService.createOrderState(request.getStatus());
        return ResponseEntity.created(URI.create("/order-states/" + state.getId())).body(state);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderState> updateOrderState(@PathVariable Long id, @RequestBody OrderStateRequest request) {
        OrderState updated = orderStateService.updateOrderState(id, request.getStatus());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderState(@PathVariable Long id) {
        orderStateService.deleteOrderState(id);
        return ResponseEntity.noContent().build();
    }
}
