package com.ornek.restorant.restorantapp.model.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderRequest {
    private Long customerId;
    private Long restaurantId;
    private String orderStatus;
    private LocalDateTime orderTime;
    private Double totalPrice;
}
