package com.ornek.restorant.restorantapp.model.dto;

import lombok.*;


@Data
public class UsersDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String city;        // Address.city
    private String district;    // Address.district
    private String fullAddress; // Address.fullAddress
    private Double latitude;
    private Double longitude;
    private Long addressId;// Address'tan alınacak
}
