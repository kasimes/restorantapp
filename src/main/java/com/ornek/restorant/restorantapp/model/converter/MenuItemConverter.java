package com.ornek.restorant.restorantapp.model.converter;

import com.ornek.restorant.restorantapp.model.entity.MenuItem;
import com.ornek.restorant.restorantapp.model.dto.MenuItemDto;
import com.ornek.restorant.restorantapp.model.entity.Menu;
import org.springframework.stereotype.Component;

@Component
public class MenuItemConverter {

    public static MenuItemDto toDto(MenuItem entity) {
        if (entity == null) return null;

        MenuItemDto dto = new MenuItemDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setCategory(entity.getCategory());
        dto.setAvailable(entity.isAvailable());
        if (entity.getMenu() != null) {
            dto.setMenuId(entity.getMenu().getId());
        }
        dto.setAvailabilityStatus(entity.getAvailabilityStatus() != null ? entity.getAvailabilityStatus() : null);
        return dto;
    }

    public static MenuItem toEntity(MenuItemDto dto, Menu menu) {
        if (dto == null) return null;

        MenuItem entity = new MenuItem();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setCategory(dto.getCategory());
        entity.setAvailable(dto.isAvailable());
        entity.setMenu(menu);
        if (dto.getAvailabilityStatus() != null) {
            entity.setAvailabilityStatus(dto.getAvailabilityStatus());
        }
        return entity;
    }
}
