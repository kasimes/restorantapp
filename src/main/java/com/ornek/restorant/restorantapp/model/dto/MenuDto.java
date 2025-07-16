package com.ornek.restorant.restorantapp.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MenuDto {


    private Long id;
    private String name;
    private String description;
    private Long restaurantId;

}
