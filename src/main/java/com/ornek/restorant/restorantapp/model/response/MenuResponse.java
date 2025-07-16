package com.ornek.restorant.restorantapp.model.response;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class MenuResponse {
    private Long id;
    private String name;
    private String description;
    private Long restaurantId;
    private LocalDateTime createdAt;
}
