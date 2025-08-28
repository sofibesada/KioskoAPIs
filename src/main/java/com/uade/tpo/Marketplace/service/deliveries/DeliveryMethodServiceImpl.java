package com.uade.tpo.Marketplace.service.deliveries;

import com.uade.tpo.Marketplace.entity.DeliveryMethod;
import com.uade.tpo.Marketplace.repository.deliveries.DeliveryMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryMethodServiceImpl implements DeliveryMethodService {

    @Autowired
    private DeliveryMethodRepository deliveryMethodRepository;

    @Override
    public List<DeliveryMethod> getDeliveryMethods() {
        return deliveryMethodRepository.findAll();
    }

    @Override
    public Optional<DeliveryMethod> getDeliveryMethodById(Long id) {
        return deliveryMethodRepository.findById(id);
    }

    @Override
    public DeliveryMethod createDeliveryMethod(String name) {
        DeliveryMethod method = new DeliveryMethod();
        method.setName(name);
        return deliveryMethodRepository.save(method);
    }

    @Override
    public DeliveryMethod updateDeliveryMethod(Long id, String name) {
        DeliveryMethod method = deliveryMethodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Método de entrega no encontrado con id: " + id));
        method.setName(name);
        return deliveryMethodRepository.save(method);
    }

    @Override
    public void deleteDeliveryMethod(Long id) {
        deliveryMethodRepository.deleteById(id);
    }
}

