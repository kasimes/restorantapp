package com.ornek.restorant.restorantapp.service;

import com.ornek.restorant.restorantapp.model.RestaurantDto;
import java.util.List;


public interface RestorantService {
    RestaurantDto save(RestaurantDto  restaurantDto );
    List<RestaurantDto> findAll();
    RestaurantDto findById(Long id);
    RestaurantDto update(Long id, RestaurantDto dto);
    void delete(Long id);
}
