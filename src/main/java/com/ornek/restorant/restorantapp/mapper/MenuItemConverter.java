package com.ornek.restorant.restorantapp.mapper;

import com.ornek.restorant.restorantapp.entity.Menuitem;
import com.ornek.restorant.restorantapp.model.MenuItemDto;
import org.springframework.stereotype.Component;

@Component
public class MenuItemConverter {

    public Menuitem convert(MenuItemDto menuitemDto) {
        Menuitem menuitem = new Menuitem();
        menuitem.setId(menuitemDto.getId());

        return menuitem;
    }

    public MenuItemDto convert(Menuitem menuitem) {
        MenuItemDto menuItemDto = new MenuItemDto();
        menuItemDto.setId(menuitem.getId());

        return menuItemDto;
    }
}
