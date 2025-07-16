package com.ornek.restorant.restorantapp.service;

import com.ornek.restorant.restorantapp.model.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {

    List<RestaurantDto> getAllRestaurants();

    RestaurantDto getRestaurantById(Long id);

    RestaurantDto createRestaurant(RestaurantDto restaurantDto);

    RestaurantDto updateRestaurant(Long id, RestaurantDto restaurantDto);

    void deleteRestaurant(Long id);
}
