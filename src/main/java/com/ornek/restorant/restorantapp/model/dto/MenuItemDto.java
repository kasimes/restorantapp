package com.ornek.restorant.restorantapp.model.dto;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data

public class MenuItemDto {

    private Long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private boolean isAvailable;
    @NotNull
    private Long menuId;
    private String availabilityStatus;

}
