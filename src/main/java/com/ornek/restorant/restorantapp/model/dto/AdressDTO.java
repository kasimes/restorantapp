package com.ornek.restorant.restorantapp.model.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdressDTO {


    private Long id;
    private String city;
    private String district;
    private Double latitude;
    private Double longitude;
    private String fullAddress;
}
