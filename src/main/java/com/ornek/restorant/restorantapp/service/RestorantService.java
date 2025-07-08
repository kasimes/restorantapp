package com.ornek.restorant.restorantapp.service;

import com.ornek.restorant.restorantapp.entity.Restaurant;
import java.util.List;


public interface RestorantService {
    Restaurant save(Restaurant restaurant);
    List<Restaurant> findAll();
    Restaurant findById(Long id);
    Restaurant update(Long id, Restaurant restaurant);
    void delete(Long id);
}
