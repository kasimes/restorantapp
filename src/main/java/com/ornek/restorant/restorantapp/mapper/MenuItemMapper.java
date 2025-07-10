package com.ornek.restorant.restorantapp.mapper;

import com.ornek.restorant.restorantapp.entity.Menuitem;
import com.ornek.restorant.restorantapp.model.MenuItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    MenuItemMapper INSTANCE = Mappers.getMapper(MenuItemMapper.class);

    @Mapping(source="id", target = "menuId")
    MenuItemDto dto(Menuitem menuitem);

    @Mapping(source = "menuId",target = "id")
    Menuitem toEntity(MenuItemDto menuitemDto) ;

}
