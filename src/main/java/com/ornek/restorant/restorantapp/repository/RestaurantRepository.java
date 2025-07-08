package com.ornek.restorant.restorantapp.repository;

import com.ornek.restorant.restorantapp.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}

