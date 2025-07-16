package com.ornek.restorant.restorantapp.model.response;

import lombok.Data;

@Data
public class MenuItemResponse {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private boolean isAvailable;
    private Long menuId;
    private String availabilityStatus;
}
