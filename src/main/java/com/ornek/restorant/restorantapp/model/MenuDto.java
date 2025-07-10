package com.ornek.restorant.restorantapp.model;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class MenuDto {


    private Long id;
    private String name;
    private String description;
    private Long restaurantId;

}
