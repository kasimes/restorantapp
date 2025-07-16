package com.ornek.restorant.restorantapp.model.request;

import lombok.Data;

@Data
public class MenuItemRequest {
    private String name;
    private String description;
    private double price;
    private String category;
    private Boolean isAvailable;
    private Long menuId;
    private String availabilityStatus;

}
