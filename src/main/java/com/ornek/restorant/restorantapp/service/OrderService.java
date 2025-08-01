package com.ornek.restorant.restorantapp.service;

import com.ornek.restorant.restorantapp.model.dto.OrderDto;
import com.ornek.restorant.restorantapp.model.dto.OrderItemDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrders();
    OrderDto createOrder(OrderDto orderDto, List<OrderItemDto>  orderItemDto);
    OrderDto updateOrder(Long id, OrderDto orderDto);
    OrderDto getOrderById(long id);
    void deleteOrderById(long id);
}
