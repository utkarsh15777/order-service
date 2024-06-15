package com.tuppertech.microservices.order.repository;

import com.tuppertech.microservices.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
