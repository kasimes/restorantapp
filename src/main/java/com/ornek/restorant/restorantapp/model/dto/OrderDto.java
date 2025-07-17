package com.ornek.restorant.restorantapp.model.dto;
import com.ornek.restorant.restorantapp.model.enums.OrderStatus;
import java.time.LocalDateTime;

import lombok.*;


@Data

public class OrderDto {
    private Long id;
    private Long customerId;
    private Long branchId;
    private OrderStatus orderStatus;
    private LocalDateTime orderTime;
    private Double totalPrice;


}
