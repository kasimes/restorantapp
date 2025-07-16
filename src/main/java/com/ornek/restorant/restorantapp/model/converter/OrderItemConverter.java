package com.ornek.restorant.restorantapp.model.converter;

import com.ornek.restorant.restorantapp.model.dto.OrderItemDto;
import com.ornek.restorant.restorantapp.model.entity.MenuItem;

import com.ornek.restorant.restorantapp.model.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {

    public static OrderItemDto toDto(OrderItem orderitem) {
        if (orderitem == null) return null;
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderitem.getId());
        orderItemDto.setOrderId(orderitem.getOrder().getId());
        orderItemDto.setMenuItemId(orderitem.getMenuitem().getId());
        orderItemDto.setQuantity(orderitem.getQuantity());
        orderItemDto.setPrice(orderitem.getPrice());
        return orderItemDto;
    }

    public static OrderItem toEntity(OrderItemDto orderItemDto) {
        if (orderItemDto == null) return null;
        OrderItem orderitem = new OrderItem();
        MenuItem  menuitem = new MenuItem();
        orderitem.setId(orderItemDto.getId());
        orderitem.setOrder(orderitem.getOrder());
        orderitem.setMenuitem(menuitem);
        orderitem.setQuantity(orderItemDto.getQuantity());
        orderitem.setPrice(orderItemDto.getPrice());
        return orderitem;
    }
}
