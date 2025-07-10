package com.ornek.restorant.restorantapp.model;


import com.ornek.restorant.restorantapp.enums.AvailabilityStatus;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDto {

    private Long id;
    private Long menuId;
    private String name;
    private String description;
    private double price;
    private String category;
    private AvailabilityStatus availabilityStatus;


}
