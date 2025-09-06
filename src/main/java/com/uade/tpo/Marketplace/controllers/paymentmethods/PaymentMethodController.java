package com.uade.tpo.Marketplace.controllers.paymentmethods;


import com.uade.tpo.Marketplace.entity.PaymentMethod;
import com.uade.tpo.Marketplace.service.paymentmethods.PaymentMethodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping
    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethodService.getPaymentMethods();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethod> getPaymentMethodById(@PathVariable Long id) {
        return paymentMethodService.getPaymentMethodById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PaymentMethod> createPaymentMethod(@RequestBody PaymentMethodRequest request) {
        PaymentMethod method = paymentMethodService.createPaymentMethod(
                request.getType(),
                request.getDetails()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(method);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethod> updatePaymentMethod(@PathVariable Long id, @RequestBody PaymentMethodRequest request) {
        PaymentMethod method = paymentMethodService.updatePaymentMethod(
                id,
                request.getType(),
                request.getDetails()
        );
        return ResponseEntity.ok(method);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable Long id) {
        paymentMethodService.deletePaymentMethod(id);
        return ResponseEntity.noContent().build();
    }
}

