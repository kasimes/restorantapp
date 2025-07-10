package com.ornek.restorant.restorantapp.service;

import com.ornek.restorant.restorantapp.entity.Menuitem;
import com.ornek.restorant.restorantapp.entity.Restaurant;
import com.ornek.restorant.restorantapp.mapper.MenuItemConverter;
import com.ornek.restorant.restorantapp.mapper.RestorantMapper;
import com.ornek.restorant.restorantapp.model.MenuItemDto;
import com.ornek.restorant.restorantapp.model.RestaurantDto;
import com.ornek.restorant.restorantapp.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RestorantServiceImpl implements RestorantService {

    private final RestaurantRepository restaurantRepository;
    private final RestorantMapper restorantMapper;
    private final MenuItemConverter menuItemConverter;

    public RestorantServiceImpl(RestaurantRepository restaurantRepository, RestorantMapper restorantMapper, MenuItemConverter menuItemConverter) {
        this.restaurantRepository = restaurantRepository;
        this.restorantMapper = restorantMapper;
        this.menuItemConverter = menuItemConverter;
    }

    @Override
    public RestaurantDto save(RestaurantDto dto) {
        Restaurant entity = restorantMapper.toEntity(dto);
        Restaurant saved = restaurantRepository.save(entity);
        return restorantMapper.toDto(saved);
    }

    @Override
    public List<RestaurantDto> findAll() {
        Collectors Collectors = null;
        return restaurantRepository.findAll()
                .stream()
                .map(restorantMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public RestaurantDto findById(Long id) {
        return restaurantRepository.findById(id)
                .map(restorantMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
    }

    @Override
    public RestaurantDto update(Long id, RestaurantDto dto) {
        Restaurant existing = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));

        existing.setName(dto.getName());
        existing.setAddress(dto.getAddress());

        Menuitem menuitem = menuItemConverter.convert(new MenuItemDto());


        Restaurant updated = restaurantRepository.save(existing);
        return restorantMapper.toDto(updated);
    }
}
