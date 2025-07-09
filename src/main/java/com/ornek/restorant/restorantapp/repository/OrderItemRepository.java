package com.ornek.restorant.restorantapp.repository;

import com.ornek.restorant.restorantapp.entity.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<Orderitem ,Long> {
}
