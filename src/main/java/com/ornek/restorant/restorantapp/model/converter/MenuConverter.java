package com.ornek.restorant.restorantapp.model.converter;

import com.ornek.restorant.restorantapp.model.dto.MenuDto;


import com.ornek.restorant.restorantapp.model.entity.Menu;

import org.springframework.stereotype.Component;

@Component
public class MenuConverter {

    public static MenuDto toDto(Menu menu) {
        if(menu == null) return null;
        MenuDto dto = new MenuDto();
        dto.setId(menu.getId());
        dto.setName(menu.getName());
        dto.setDescription(menu.getDescription());
        if(menu.getRestaurant() != null) {
            dto.setRestaurantId(menu.getRestaurant().getId());
        }
        return dto;
    }

//    public Menu toEntity(MenuDto menuDto, Restaurant restaurant) {
//        if(menuDto == null) return null;
//
//        Menu menu = new Menu();
//        menu.setId(menuDto.getId());
//        menu.setName(menuDto.getName());
//        menu.setDescription(menuDto.getDescription());
//        menu.setRestaurant(restaurant);
//        return toEntity;
//
//    }
}
