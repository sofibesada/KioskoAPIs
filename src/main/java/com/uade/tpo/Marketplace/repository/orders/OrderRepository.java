package com.uade.tpo.Marketplace.repository.orders;

import java.security.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.Marketplace.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // buscar ordenes activas (no eliminadas)
    List<Order> findByDeleteAtIsNull();

    // buscar ordenes por usuario
    List<Order> findByUserId(Long userId);

    // buscar por estado
    List<Order> findByOrderStateId(Long orderStateId);

    // buscar por método de entrega
    List<Order> findByDeliveryMethodId(Long deliveryMethodId);

    Order findByNumber(int number);

    // buscar por rango de fechas
    @Query("SELECT o FROM Order o WHERE o.created_at BETWEEN :startDate AND :endDate")
    List<Order> findOrdersBetweenDates(Timestamp startDate, Timestamp endDate);


}
