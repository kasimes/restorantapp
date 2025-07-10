package com.ornek.restorant.restorantapp.mapper;

import com.ornek.restorant.restorantapp.model.MenuDto;
import com.ornek.restorant.restorantapp.entity.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    @Mapping(source = "restaurant.id", target = "restaurantId")
    MenuDto toDto(Menu menu);

    @Mapping(source = "restaurantId", target = "restaurant.id")
    Menu toEntity(MenuDto dto);

}
