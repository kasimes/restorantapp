package com.ornek.restorant.restorantapp.model.response;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RestaurantResponse {
    private Long id;
    private String name;
    private String address;
    private Long phone;
    private String email;
    private LocalDateTime createdAt;
}
