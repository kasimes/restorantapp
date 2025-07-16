package com.ornek.restorant.restorantapp.model.response;


import lombok.Data;

@Data
public class OrderItemResponse {

    private Long id;
    private Long orderId;
    private Long menuItemId;
    private Integer quantity;
    private double price;
}
