package com.ornek.restorant.restorantapp.mapper;

import com.ornek.restorant.restorantapp.entity.Restaurant;
import com.ornek.restorant.restorantapp.model.RestaurantDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestorantMapper {

    RestaurantDto toDto(Restaurant restaurant);

    Restaurant toEntity(RestaurantDto restaurantDto);
}
