package com.ornek.restorant.restorantapp.model.dto;
import com.ornek.restorant.restorantapp.model.enums.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;

import lombok.*;


@Data

public class OrderDto {
    private Long id;
    private Long customerId;
    private Long branchId;
    private OrderStatus orderStatus;
    private LocalDateTime orderTime;
    private Double totalPrice;
    private List<OrderItemDto> orderItems;
    private String discountType; // yuzde veya eşik indiirm



}
