package com.ornek.restorant.restorantapp.model.dto;


import com.ornek.restorant.restorantapp.model.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {

    private Long id;
    private String name;
    private Long restaurantId;
    private BigDecimal minimumOrderAmount;
    private String openingTime;  // veya LocalTime da olabilir
    private String closingTime;

    private AdressDTO addressDTO;


}
