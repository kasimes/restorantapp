package com.ornek.restorant.restorantapp.service;

import com.ornek.restorant.restorantapp.model.dto.MenuDto;

import java.util.List;


public interface MenuService {

    MenuDto getMenuById(Long id);
    MenuDto createMenu(MenuDto menuDto);
    MenuDto updateMenu(MenuDto menuDto);
    void deleteMenu(Long id);

    List<MenuDto> getAllMenus();
}
