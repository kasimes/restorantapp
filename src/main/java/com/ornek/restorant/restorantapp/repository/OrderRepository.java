package com.ornek.restorant.restorantapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ornek.restorant.restorantapp.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
