package com.uade.tpo.Marketplace.service.orderstates;

import com.uade.tpo.Marketplace.entity.OrderState;
import java.util.List;
import java.util.Optional;

public interface OrderStateService {
    List<OrderState> getOrderStates();
    Optional<OrderState> getOrderStateById(Long id);
    OrderState createOrderState(String status);
    OrderState updateOrderState(Long id, String status);
    void deleteOrderState(Long id);
}
