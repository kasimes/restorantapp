package com.ornek.restorant.restorantapp.model.request;

import lombok.Data;

@Data
public class RestaurantRequest {
    private String name;
    private String address;
    private Long phone;
    private String email;
}
