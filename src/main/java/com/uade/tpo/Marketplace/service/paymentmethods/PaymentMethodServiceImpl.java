package com.uade.tpo.Marketplace.service.paymentmethods;

import com.uade.tpo.Marketplace.entity.PaymentMethod;
import com.uade.tpo.Marketplace.repository.paymentmethods.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public Optional<PaymentMethod> getPaymentMethodById(Long id) {
        return paymentMethodRepository.findById(id);
    }

    @Override
    public PaymentMethod createPaymentMethod(String type, String details) {
        PaymentMethod method = new PaymentMethod();
        method.setType(type);
        method.setDetails(details);
        return paymentMethodRepository.save(method);
    }

    @Override
    public PaymentMethod updatePaymentMethod(Long id, String type, String details) {
        PaymentMethod method = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Método de pago no encontrado con id: " + id));

        method.setType(type);
        method.setDetails(details);

        return paymentMethodRepository.save(method);
    }

    @Override
    public void deletePaymentMethod(Long id) {
        paymentMethodRepository.deleteById(id);
    }
}

