package com.ornek.restorant.restorantapp.service;

import com.ornek.restorant.restorantapp.model.dto.MenuItemDto;

import java.util.List;

public interface MenuItemService {
    List <MenuItemDto> getAllMenuItems();
    MenuItemDto getMenuItemById(long id);
    MenuItemDto createMenuItem(MenuItemDto menuItem);
    MenuItemDto updateMenuItem( Long id ,MenuItemDto menuItemDto);
    void deleteMenuItemById(long id);

}
