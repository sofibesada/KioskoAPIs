package com.uade.tpo.Marketplace.controllers.categories.deliveries;

import com.uade.tpo.Marketplace.entity.DeliveryMethod;
import com.uade.tpo.Marketplace.service.deliveries.DeliveryMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/delivery-methods")
public class DeliveryMethodController {

    @Autowired
    private DeliveryMethodService deliveryMethodService;

    @GetMapping
    public ResponseEntity<List<DeliveryMethod>> getDeliveryMethods() {
        return ResponseEntity.ok(deliveryMethodService.getDeliveryMethods());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryMethod> getDeliveryMethodById(@PathVariable Long id) {
        return deliveryMethodService.getDeliveryMethodById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DeliveryMethod> createDeliveryMethod(@RequestBody DeliveryMethodRequest request) {
        DeliveryMethod method = deliveryMethodService.createDeliveryMethod(request.getName());
        return ResponseEntity.created(URI.create("/delivery-methods/" + method.getId())).body(method);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryMethod> updateDeliveryMethod(@PathVariable Long id, @RequestBody DeliveryMethodRequest request) {
        DeliveryMethod updated = deliveryMethodService.updateDeliveryMethod(id, request.getName());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeliveryMethod(@PathVariable Long id) {
        deliveryMethodService.deleteDeliveryMethod(id);
        return ResponseEntity.noContent().build();
    }
}
