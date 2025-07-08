package com.ornek.restorant.restorantapp.Model;

public class ProductDto {
    private Long id;
    private String restaurantName;
    private String name;

    public ProductDto() {}

    public ProductDto(Long id, String restaurantName, String name) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
