package com.uade.tpo.Marketplace.service.deliveries;

import com.uade.tpo.Marketplace.entity.DeliveryMethod;
import java.util.List;
import java.util.Optional;

public interface DeliveryMethodService {
    List<DeliveryMethod> getDeliveryMethods();
    Optional<DeliveryMethod> getDeliveryMethodById(Long id);
    DeliveryMethod createDeliveryMethod(String name);
    DeliveryMethod updateDeliveryMethod(Long id, String name);
    void deleteDeliveryMethod(Long id);
}
