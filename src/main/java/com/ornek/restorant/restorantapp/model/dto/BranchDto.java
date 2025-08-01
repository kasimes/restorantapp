package com.ornek.restorant.restorantapp.model.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BranchDto {

    private Long id;
    private String name;
    private Long restaurantId;
    private Long addressId;
    private String city;      // Address'tan alınacak
    private String district;
    private String FullAddress;// Address'tan alınacak
    private BigDecimal minimumOrderAmount;
    private String openingTime;  // veya LocalTime da olabilir
    private String closingTime;

    private Double latitude;
    private Double longitude;
}
