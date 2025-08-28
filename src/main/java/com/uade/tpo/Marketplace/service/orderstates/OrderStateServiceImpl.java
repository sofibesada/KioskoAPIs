package com.uade.tpo.Marketplace.service.orderstates;

import com.uade.tpo.Marketplace.entity.OrderState;
import com.uade.tpo.Marketplace.repository.orderstates.OrderStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderStateServiceImpl implements OrderStateService {

    @Autowired
    private OrderStateRepository orderStateRepository;

    @Override
    public List<OrderState> getOrderStates() {
        return orderStateRepository.findAll();
    }

    @Override
    public Optional<OrderState> getOrderStateById(Long id) {
        return orderStateRepository.findById(id);
    }

    @Override
    public OrderState createOrderState(String status) {
        if (!orderStateRepository.findByStatus(status).isEmpty()) {
            throw new IllegalArgumentException("El estado '" + status + "' ya existe.");
        }
        OrderState state = new OrderState();
        state.setStatus(status);
        return orderStateRepository.save(state);
    }

    @Override
    public OrderState updateOrderState(Long id, String status) {
        OrderState state = orderStateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado con id: " + id));

        if (!state.getStatus().equals(status) && !orderStateRepository.findByStatus(status).isEmpty()) {
            throw new IllegalArgumentException("El estado '" + status + "' ya existe.");
        }

        state.setStatus(status);
        return orderStateRepository.save(state);
    }

    @Override
    public void deleteOrderState(Long id) {
        if (!orderStateRepository.existsById(id)) {
            throw new IllegalArgumentException("Estado no encontrado con id: " + id);
        }
        orderStateRepository.deleteById(id);
    }
}
