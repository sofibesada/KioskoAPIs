package com.uade.tpo.Marketplace.service.orders;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.Marketplace.entity.*;
import com.uade.tpo.Marketplace.repository.orders.OrderRepository;
import com.uade.tpo.Marketplace.repository.users.UserRepository;
import com.uade.tpo.Marketplace.repository.deliveries.DeliveryMethodRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired private OrderRepository orderRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private DeliveryMethodRepository deliveryMethodRepository;

    @Override
    public List<Order> getOrders() {
        return orderRepository.findByDeleteAtIsNull();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<Order> getOrderByNumber(int number) {
        return Optional.ofNullable(orderRepository.findByNumber(number));
    }



    @Override
    public Order createOrder(Long userId, Long deliveryMethodId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + userId));
        DeliveryMethod deliveryMethod = deliveryMethodRepository.findById(deliveryMethodId)
                .orElseThrow(() -> new IllegalArgumentException("Método de entrega no encontrado con id: " + deliveryMethodId));
    

             
        Order order = new Order();
        order.setUser(user);
        order.setDeliveryMethod(deliveryMethod);
        order.setCreated_at(Timestamp.from(Instant.now()));
        order.setTotalAmount(deliveryMethod.getPrice());
        

        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long id, Long userId, Long deliveryMethodId) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada con id: " + id));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + userId));
        DeliveryMethod deliveryMethod = deliveryMethodRepository.findById(deliveryMethodId)
                .orElseThrow(() -> new IllegalArgumentException("Método de entrega no encontrado con id: " + deliveryMethodId));


        order.setUser(user);
        order.setDeliveryMethod(deliveryMethod);
        order.setUpdated_at(Timestamp.from(Instant.now()));
        order.setTotalAmount(calculateTotalAmount(order));

        return orderRepository.save(order);
    }

    @Override
    public float calculateTotalAmount(Order order) {
        float total = 0f;
        if (order.getOrderDetail() != null) {
            for (OrderDetail detail : order.getOrderDetail()) {
                total += detail.getSubtotal(); 
            }
        }
        if (order.getDeliveryMethod() != null) {
            total += order.getDeliveryMethod().getPrice(); 
        }
        return total;
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada con id: " + id));
        order.setDeleteAt(Timestamp.from(Instant.now()));
        orderRepository.save(order);
    }


    public Order updateOrderState(Long orderId, OrderState newState) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        OrderState currentState = order.getState();

        // Validar transiciones
        switch (currentState) {
                case PENDIENTE:
                if (newState != OrderState.PAGADO && newState != OrderState.CANCELADO) {
                        throw new IllegalStateException("Una orden pendiente solo puede pasar a PAGO o CANCELADA");
                }
                break;
                case PAGADO:
                if (newState != OrderState.ENVIADO) {
                        throw new IllegalStateException("Una orden pagada solo puede pasar a ENVIADA");
                }
                break;
                case ENVIADO:
                if (newState != OrderState.ENTREGADO) {
                        throw new IllegalStateException("Una orden enviada solo puede pasar a ENTREGADA");
                }
                break;
                case ENTREGADO:
                case CANCELADO:
                throw new IllegalStateException("Una orden entregada o cancelada no puede cambiar de estado");
        }

        order.setState(newState);
        return orderRepository.save(order);
     }

}
