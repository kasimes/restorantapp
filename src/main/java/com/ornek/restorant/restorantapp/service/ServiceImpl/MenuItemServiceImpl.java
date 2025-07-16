package com.ornek.restorant.restorantapp.service.ServiceImpl;

import com.ornek.restorant.restorantapp.model.converter.MenuItemConverter;
import com.ornek.restorant.restorantapp.model.dto.MenuItemDto;
import com.ornek.restorant.restorantapp.model.entity.Menu;
import com.ornek.restorant.restorantapp.model.entity.MenuItem;
import com.ornek.restorant.restorantapp.model.enums.AvailabilityStatus;
import com.ornek.restorant.restorantapp.repository.MenuItemRepository;
import com.ornek.restorant.restorantapp.repository.MenuRepository;
import com.ornek.restorant.restorantapp.service.MenuItemService;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private  final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;

    public MenuItemServiceImpl(MenuRepository menuRepository, MenuItemRepository menuItemRepository) {
        this.menuRepository = menuRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public java.util.List<MenuItemDto> getAllMenuItems() {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        return menuItems.stream()
                .map(MenuItemConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MenuItemDto getMenuItemById(long id) {

        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem not found with id: " + id));
        return MenuItemConverter.toDto(menuItem);
    }

    @Override
    public MenuItemDto createMenuItem(MenuItemDto menuItemDto) {
        Menu menu = menuRepository.findById(menuItemDto.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + menuItemDto.getMenuId()));

        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemDto.getName());
        menuItem.setPrice(menuItemDto.getPrice());
        menuItem.setMenu(menu);

        menuItem.setAvailabilityStatus(
                menuItemDto.getAvailabilityStatus() != null
                        ? AvailabilityStatus.valueOf(menuItemDto.getAvailabilityStatus())
                        : AvailabilityStatus.AVAILABLE
        );
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        return MenuItemConverter.toDto(savedMenuItem);
    }

    @Override
    public MenuItemDto updateMenuItem(Long id, MenuItemDto menuItemDto) {

        MenuItem existingMenuItem = menuItemRepository.findById(menuItemDto.getId())
                .orElseThrow(() -> new RuntimeException("MenuItem not found with id: " + menuItemDto.getId()));

        Menu menu = menuRepository.findById(menuItemDto.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + menuItemDto.getMenuId()));

        existingMenuItem.setName(menuItemDto.getName());
        existingMenuItem.setPrice(menuItemDto.getPrice());
        existingMenuItem.setMenu(menu);

        MenuItem updatedMenuItem = menuItemRepository.save(existingMenuItem);
        return MenuItemConverter.toDto(updatedMenuItem);
    }

    @Override
    public void deleteMenuItemById(long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem not found with id: " + id));
        menuItemRepository.delete(menuItem);

    }
}
