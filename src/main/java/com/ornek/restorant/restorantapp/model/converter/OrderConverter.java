package com.ornek.restorant.restorantapp.model.converter;

import com.ornek.restorant.restorantapp.model.dto.OrderDto;
import com.ornek.restorant.restorantapp.model.entity.Customer;
import com.ornek.restorant.restorantapp.model.entity.Order;
import com.ornek.restorant.restorantapp.model.entity.Restaurant;
import com.ornek.restorant.restorantapp.model.enums.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {

    public OrderDto toDto(Order entity) {
        if (entity == null) return null;

        OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setCustomerId(entity.getCustomer().getId());
        dto.setRestaurantId(entity.getRestaurant().getId());
        dto.setOrderStatus(OrderStatus.valueOf(entity.getOrderStatus().name()));
        dto.setOrderTime(entity.getOrderTime());
        dto.setTotalPrice(entity.getTotalPrice());
        return dto;
    }

    public static Order toEntity(OrderDto dto, Customer customer, Restaurant restaurant) {
        if (dto == null) return null;

        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setCustomer(customer);
        entity.setRestaurant(restaurant);
        entity.setOrderStatus(OrderStatus.valueOf(String.valueOf(dto.getOrderStatus())));
        entity.setOrderTime(dto.getOrderTime());
        entity.setTotalPrice(dto.getTotalPrice());
        return entity;
    }
}
