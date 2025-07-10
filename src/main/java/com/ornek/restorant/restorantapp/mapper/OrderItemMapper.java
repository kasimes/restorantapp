package com.ornek.restorant.restorantapp.mapper;

import com.ornek.restorant.restorantapp.entity.Orderitem;
import com.ornek.restorant.restorantapp.model.OrderDto;
import com.ornek.restorant.restorantapp.model.OrderItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source="order.id",target="orderId")
    @Mapping(source = "menuitem.id", target = "menuitemId")
    OrderItemDto toDto(Orderitem orderitem);

    @Mapping(source ="orderId" ,target ="order.id")
    @Mapping(source = "menuitemId", target = "menuitem.id")
    Orderitem toEntity(OrderItemDto dto);
}
