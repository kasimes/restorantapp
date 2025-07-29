package com.ornek.restorant.restorantapp.service;

import com.ornek.restorant.restorantapp.model.converter.MenuConverter;
import com.ornek.restorant.restorantapp.model.dto.MenuDto;
import com.ornek.restorant.restorantapp.model.entity.Branch;
import com.ornek.restorant.restorantapp.model.entity.Menu;
import com.ornek.restorant.restorantapp.model.entity.Restaurant;
import com.ornek.restorant.restorantapp.repository.BranchRepository;
import com.ornek.restorant.restorantapp.repository.MenuItemRepository;
import com.ornek.restorant.restorantapp.repository.MenuRepository;
import com.ornek.restorant.restorantapp.repository.RestaurantRepository;
import com.ornek.restorant.restorantapp.service.ServiceImpl.MenuServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestMenuServiceImpl {

    @Mock
    private MenuRepository menuRepository;
    @Mock
    private BranchRepository branchRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private MenuServiceImpl menuServiceImpl;



    private Restaurant restaurant;
    private Menu menu;
    private Branch branch;
    private MenuDto menuDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        menu = new Menu();
        menu.setId(1L);
        menu.setName("Menu 1");



        branch = new Branch();
        branch.setId(1L);
        branch.setName("Branch 1");
        menu.setBranch(branch);



        menuDto = new MenuDto();
        menuDto.setId(1L);
        menuDto.setName("Menu 1");
        menuDto.setRestaurantId(1L);

        restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Restaurant 1");


    }

    @Test
    void testGetMenuById() {
        when(menuRepository.findById(1L)).thenReturn(Optional.of(menu));
       try(  MockedStatic<MenuConverter> mockedStatic = Mockito.mockStatic(MenuConverter.class);) {
           mockedStatic.when(() -> MenuConverter.toDto(menu)).thenReturn(menuDto);
           MenuDto menuDto = menuServiceImpl.getMenuById(1L);

           assertNotNull(menuDto);
           assertEquals(menuDto.getId(),menu.getId());


       }
       verify(menuRepository,times(1)).findById(1L);

    }

    @Test
    void testGetAllMenu() {
        when(menuRepository.findAll()).thenReturn(Arrays.asList(menu));

        try(MockedStatic<MenuConverter> mockedStatic = Mockito.mockStatic(MenuConverter.class);) {
            mockedStatic.when(() -> MenuConverter.toDto(menu)).thenReturn(menuDto);

            List<MenuDto> menudto2 = menuServiceImpl.getAllMenus();

            assertNotNull(menudto2);
            assertTrue(menudto2.size()>0);
            assertEquals(1,menudto2.size());
            assertEquals("Menu 1",menudto2.get(0).getName());


        }
        verify(menuRepository,times(1)).findAll();

    }

    @Test
    void testCreateMenuItem() {

        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));

        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));

        try(MockedStatic<MenuConverter> mockedStatic = Mockito.mockStatic(MenuConverter.class)) {
            mockedStatic.when(() -> MenuConverter.toDto(menu)).thenReturn(menuDto);

            when(menuRepository.save(any(Menu.class))).thenReturn(menu);

            MenuDto menuDto1 = menuServiceImpl.createMenu(menuDto);
            assertNotNull(menuDto1);
            assertEquals("Menu 1",menuDto1.getName());

        }
        verify(menuRepository,times(1)).save(any(Menu.class));
    }

    @Test
    void testUpdateMenuItem() {
        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));

        when(menuRepository.findById(1L)).thenReturn(Optional.of(menu));

        try(MockedStatic<MenuConverter> mockedStatic = Mockito.mockStatic(MenuConverter.class)) {
            mockedStatic.when(() -> MenuConverter.toDto(menu)).thenReturn(menuDto);
            when(menuRepository.save(any(Menu.class))).thenReturn(menu);
            MenuDto menuDto1 = menuServiceImpl.updateMenu(menuDto);
            assertNotNull(menuDto1);
            assertEquals("Menu 1",menuDto1.getName());

        }
        verify(menuRepository,times(1)).save(any(Menu.class));



    }

    @Test
    void testDeleteMenuItem() {

        when(menuRepository.findById(1L)).thenReturn(Optional.of(menu));

        menuServiceImpl.deleteMenu(1L);

        verify(menuRepository,times(1)).findById(1L);
        verify(menuRepository,times(1)).delete(menu);

    }
}
