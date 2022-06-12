package com.tw.travel.ticketing.infrastructure.repository;

import com.tw.travel.ticketing.infrastructure.repository.entity.TicketingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketingOrderRepository extends JpaRepository<TicketingOrder, Long> {
    Optional<TicketingOrder> findByOrderNumber(String orderNumber);
}
