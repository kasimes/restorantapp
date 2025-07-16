package com.ornek.restorant.restorantapp.model.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderResponse {
    private Long id;
    private Long customerId;
    private Long restaurantId;
    private String orderStatus;
    private LocalDateTime orderTime;
    private Double totalPrice;
}
