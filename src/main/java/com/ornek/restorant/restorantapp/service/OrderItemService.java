package com.ornek.restorant.restorantapp.service;

import com.ornek.restorant.restorantapp.model.dto.OrderItemDto;
import com.ornek.restorant.restorantapp.model.entity.OrderItem;

import java.util.List;


public interface OrderItemService {
    List<OrderItemDto> getAllOrderItems();

    OrderItemDto getOrderItemById(Long id);

    OrderItem createOrderItem(OrderItemDto orderItemDto);

    OrderItemDto updateOrderItem(Long id, OrderItemDto orderItemDto);

    void deleteOrderItem(Long id);
}
