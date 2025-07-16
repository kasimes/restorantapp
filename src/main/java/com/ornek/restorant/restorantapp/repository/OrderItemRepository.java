package com.ornek.restorant.restorantapp.repository;

import com.ornek.restorant.restorantapp.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
