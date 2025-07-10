package com.ornek.restorant.restorantapp.model;
import com.ornek.restorant.restorantapp.enums.OrderStatus;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long customerId;
    private Long restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantPhone;

    private OrderStatus orderStatus;

    private LocalDateTime orderTime;

    private Double totalPrice;


}
