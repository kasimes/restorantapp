package com.ornek.restorant.restorantapp.model.dto;

import com.ornek.restorant.restorantapp.model.enums.Role;
import lombok.*;


@Data
public class UsersDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private Role role;
    private AdressDTO addressDTO;

}