package com.ornek.restorant.restorantapp.service;

import com.ornek.restorant.restorantapp.model.converter.MenuItemConverter;
import com.ornek.restorant.restorantapp.model.dto.MenuItemDto;
import com.ornek.restorant.restorantapp.model.entity.Menu;
import com.ornek.restorant.restorantapp.model.entity.MenuItem;
import com.ornek.restorant.restorantapp.model.enums.AvailabilityStatus;
import com.ornek.restorant.restorantapp.repository.MenuItemRepository;
import com.ornek.restorant.restorantapp.repository.MenuRepository;
import com.ornek.restorant.restorantapp.service.ServiceImpl.MenuItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

public class MenuItemServiceImplTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuItemServiceImpl menuItemServiceImpl;


    private Menu menu;
    private MenuItem menuItem;
    private MenuItemDto menuItemDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        menu = new Menu();
        menu.setId(1L);
        menu.setName("Test Menu");

        menuItem = new MenuItem();
        menuItem.setId(1L);
        menuItem.setName("Burger");
        menuItem.setDescription("Cheese Burger");
        menuItem.setPrice(15.0);
        menuItem.setCategory("Fast Food");
        menuItem.setAvailable(true);
        menuItem.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
        menuItem.setMenu(menu);

        menuItemDto = new MenuItemDto();
        menuItemDto.setId(1L);
        menuItemDto.setName("Burger");
        menuItemDto.setDescription("Cheese Burger");
        menuItemDto.setPrice(15.0);
        menuItemDto.setCategory("Fast Food");
        menuItemDto.setAvailable(true);
        menuItemDto.setAvailabilityStatus(AvailabilityStatus.valueOf(AvailabilityStatus.AVAILABLE.name()));
        menuItemDto.setMenuId(1L);
    }

    @Test
    void testGetMenuItemById() {
        try (MockedStatic<MenuItemConverter> mockedStatic = mockStatic(MenuItemConverter.class)) {
            mockedStatic.when(() -> MenuItemConverter.toDto(menuItem)).thenReturn(menuItemDto);

            when(menuItemRepository.findById(1L)).thenReturn(Optional.of(menuItem));
            MenuItemDto menuItemDto1 = menuItemServiceImpl.getMenuItemById(1L);

            assertNotNull(menuItemDto1);
            assertEquals("Burger", menuItemDto1.getName()); // menuItemDto içinde "Burger" var
            verify(menuItemRepository, times(1)).findById(1L);
        }

    }

    @Test
    void testGetAllMenuItems() {
        try (MockedStatic<MenuItemConverter> mockedStatic = mockStatic(MenuItemConverter.class)) {
            when(menuItemRepository.findAll()).thenReturn(List.of(menuItem));

            mockedStatic.when(() -> MenuItemConverter.toDto(menuItem)).thenReturn(menuItemDto);

            List<MenuItemDto> menuItemDtoList = menuItemServiceImpl.getAllMenuItems();

            assertNotNull(menuItemDtoList);
            assertFalse(menuItemDtoList.isEmpty());
            assertEquals(1, menuItemDtoList.size());
            assertEquals("Burger", menuItemDtoList.get(0).getName());

            verify(menuItemRepository, times(1)).findAll();
        }
    }

    @Test
    void testCreateMenuItem() {
        // menu nesnesi var, id 1L
        when(menuRepository.findById(1L)).thenReturn(Optional.of(menu));

        try (MockedStatic<MenuItemConverter> mockedStatic = mockStatic(MenuItemConverter.class)) {
            // toEntity çağrısı esnek olsun, herhangi dto ve menu parametresi için
            mockedStatic.when(() -> MenuItemConverter.toEntity(any(MenuItemDto.class), any(Menu.class))).thenReturn(menuItem);

            when(menuItemRepository.save(any(MenuItem.class))).thenReturn(menuItem);

            mockedStatic.when(() -> MenuItemConverter.toDto(any(MenuItem.class))).thenReturn(menuItemDto);

            MenuItemDto result = menuItemServiceImpl.createMenuItem(menuItemDto);

            assertNotNull(result);
            assertEquals("Burger", result.getName());
            verify(menuRepository, times(1)).findById(1L);
            verify(menuItemRepository, times(1)).save(any(MenuItem.class));
        }
    }

    @Test
    void testUpdateMenuItem() {

        when(menuRepository.findById(1L)).thenReturn(Optional.of(menu));
        when(menuItemRepository.findById(1L)).thenReturn(Optional.of(menuItem));
        try (MockedStatic<MenuItemConverter> mockedStatic = mockStatic(MenuItemConverter.class)) {
            when(menuItemRepository.findById(1L)).thenReturn(Optional.of(menuItem));
            mockedStatic.when(() -> MenuItemConverter.toEntity(any(MenuItemDto.class), any(Menu.class))).thenReturn(menuItem);
            when(menuItemRepository.save(any(MenuItem.class))).thenReturn(menuItem);
            mockedStatic.when(() -> MenuItemConverter.toDto(any(MenuItem.class))).thenReturn(menuItemDto);

            MenuItemDto menuItemDto2 = menuItemServiceImpl.updateMenuItem(1L, menuItemDto);

            assertNotNull(menuItemDto2);
            assertEquals("Burger", menuItemDto2.getName());
            verify(menuItemRepository, times(1)).findById(1L);
            verify(menuItemRepository, times(1)).save(any(MenuItem.class));

        }
    }

    @Test
    void testDeleteMenuItem() {
        when(menuRepository.findById(1L)).thenReturn(Optional.of(menu));
        when(menuItemRepository.findById(1L)).thenReturn(Optional.of(menuItem));

        doNothing().when(menuItemRepository).deleteById(1L);
        menuItemServiceImpl.deleteMenuItemById(1L);

        verify(menuItemRepository, times(1)).findById(1L);
        verify(menuItemRepository, times(1)).delete(menuItem);


    }



}
