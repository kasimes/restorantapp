package com.ornek.restorant.restorantapp.model.converter;

import com.ornek.restorant.restorantapp.model.dto.RestaurantDto;
import com.ornek.restorant.restorantapp.model.entity.Restaurant;
public class RestaurantConverter {

    public static RestaurantDto toDto(Restaurant restaurant) {
        if (restaurant == null) return null;
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setAddress(restaurant.getAddress());
        restaurantDto.setPhone(restaurant.getPhone());
        return restaurantDto;
    }
    public static Restaurant toEntity(RestaurantDto restaurantDto) {
        if (restaurantDto == null) return null;
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDto.getId());
        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setPhone(restaurantDto.getPhone());
        return restaurant;
    }
}
