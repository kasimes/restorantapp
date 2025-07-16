package com.ornek.restorant.restorantapp.model.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Getter
@Setter

public class OrderItemDto {
    private  Long id;
    private Long orderId;
    private Long menuItemId;
    private Integer quantity;
    private double price;

}
