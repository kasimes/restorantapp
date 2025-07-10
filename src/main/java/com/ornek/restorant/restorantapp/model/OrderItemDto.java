package com.ornek.restorant.restorantapp.model;

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
    private Long menuitemId;
    private Integer quantity;
    private double price;

}
