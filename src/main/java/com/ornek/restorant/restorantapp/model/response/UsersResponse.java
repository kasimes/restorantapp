package com.ornek.restorant.restorantapp.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsersResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private LocalDateTime createdAt;
}
