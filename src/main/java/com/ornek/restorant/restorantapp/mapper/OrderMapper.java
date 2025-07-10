package com.ornek.restorant.restorantapp.mapper;

import com.ornek.restorant.restorantapp.entity.Order;
import com.ornek.restorant.restorantapp.model.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "restaurant.id", target = "restaurantId")
    OrderDto toDto(Order order);

    @Mapping(source = "customerId",target = "customer.id")
    @Mapping(source = "restaurantId", target = "restaurant.id")
    Order toEntity(OrderDto dto);

}
