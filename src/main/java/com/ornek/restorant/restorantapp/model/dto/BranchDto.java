package com.ornek.restorant.restorantapp.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchDto {

    private Long id;
    private String name;
    private Long restaurantId;

}
