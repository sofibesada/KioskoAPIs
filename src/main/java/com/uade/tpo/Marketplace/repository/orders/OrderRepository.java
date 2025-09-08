package com.uade.tpo.Marketplace.repository.orders;

import java.security.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.Marketplace.entity.Order;
import com.uade.tpo.Marketplace.entity.OrderState;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByDeleteAtIsNull();
    List<Order> findByUserId(Long userId);
    List<Order> findByState(OrderState state);
    List<Order> findByDeliveryMethodId(Long deliveryMethodId);
    Order findByNumber(int number);
    @Query("SELECT o FROM Order o WHERE o.created_at BETWEEN :startDate AND :endDate")
    List<Order> findOrdersBetweenDates(Timestamp startDate, Timestamp endDate);


}
