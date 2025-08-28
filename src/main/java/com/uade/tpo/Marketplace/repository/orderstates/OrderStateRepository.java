package com.uade.tpo.Marketplace.repository.orderstates;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.Marketplace.entity.OrderState;

@Repository
public interface OrderStateRepository extends JpaRepository<OrderState, Long> {
    List<OrderState> findByStatus(String status);
}