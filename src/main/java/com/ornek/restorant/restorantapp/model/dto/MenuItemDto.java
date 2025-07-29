package com.ornek.restorant.restorantapp.model.dto;

import com.ornek.restorant.restorantapp.model.enums.AvailabilityStatus;
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

    private Long menuId;
    private AvailabilityStatus availabilityStatus;

}
