package com.ornek.restorant.restorantapp.service.ServiceImpl;

import com.ornek.restorant.restorantapp.exception.MenuNotFoundException;
import com.ornek.restorant.restorantapp.exception.RestaurantNotFoundException;
import com.ornek.restorant.restorantapp.model.converter.MenuConverter;
import com.ornek.restorant.restorantapp.model.dto.MenuDto;
import com.ornek.restorant.restorantapp.model.entity.Menu;
import com.ornek.restorant.restorantapp.model.entity.Restaurant;
import com.ornek.restorant.restorantapp.repository.MenuRepository;
import com.ornek.restorant.restorantapp.repository.RestaurantRepository;
import com.ornek.restorant.restorantapp.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuServiceImpl(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }
    @Override
    public List<MenuDto> getAllMenus()
    {
        return menuRepository.findAll().stream()
                .map(MenuConverter::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public MenuDto getMenuById(Long id){
        Menu menu = menuRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Menu Not Found" + id));
        return MenuConverter.toDto(menu);
    }

    @Override
    public MenuDto createMenu(MenuDto menuDto) {
        Restaurant restaurant = restaurantRepository.findById(menuDto.getRestaurantId())
                .orElseThrow(()-> new RuntimeException("Restaurant Not Found" + menuDto.getRestaurantId()));
        Menu menu = new Menu();
        menu.setName(menuDto.getName());
        menu.setDescription(menuDto.getDescription());

        Menu savedMenu = menuRepository.save(menu);
        return MenuConverter.toDto(savedMenu);
    }
    @Override
    public MenuDto updateMenu(MenuDto menuDto) {
        Menu menu = menuRepository.findById(menuDto.getId())
                .orElseThrow(() -> new MenuNotFoundException("Menu not found with id: " + menuDto.getId()));

        Restaurant restaurant = restaurantRepository.findById(menuDto.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + menuDto.getRestaurantId()));

        menu.setName(menuDto.getName());
        menu.setDescription(menuDto.getDescription());


        Menu updatedMenu = menuRepository.save(menu);
        return MenuConverter.toDto(updatedMenu);
    }
    @Override
    public void deleteMenu(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new MenuNotFoundException("Menu not found with id: " + id));
        menuRepository.delete(menu);
    }

}
