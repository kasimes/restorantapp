package com.ornek.restorant.restorantapp.model.dto;


import lombok.Data;

@Data
public class RestaurantDto {
    private Long id;
    private String name;
    private String address;
    private Long phone;
    private String email;
}
