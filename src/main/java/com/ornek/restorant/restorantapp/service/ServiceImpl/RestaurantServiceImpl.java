package com.ornek.restorant.restorantapp.service.ServiceImpl;

import com.ornek.restorant.restorantapp.exception.notfound.RestaurantNotFoundException;
import com.ornek.restorant.restorantapp.model.dto.RestaurantDto;
import com.ornek.restorant.restorantapp.model.entity.Restaurant;
import com.ornek.restorant.restorantapp.repository.RestaurantRepository;
import com.ornek.restorant.restorantapp.service.RestaurantService;
import com.ornek.restorant.restorantapp.model.converter.RestaurantConverter;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository  restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    @Cacheable(value = "allRestaurants")
    public List<RestaurantDto> getAllRestaurants() {

        return restaurantRepository.findAll()
                .stream()
                .map(RestaurantConverter::toDto)
                .collect(Collectors.toList());
    }
    @Override
    @Cacheable(value = "restaurants",key="#id")
    public RestaurantDto getRestaurantById(Long id) {
        Restaurant  restaurant = restaurantRepository.findById(id)
                .orElseThrow(()-> new RestaurantNotFoundException("Restaurant not found"));
        return RestaurantConverter.toDto(restaurant);

    }

    @Override
    @CacheEvict(value = {"restaurants","allRestaurants"},allEntries = true)
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = RestaurantConverter.toEntity(restaurantDto);
        Restaurant saved = restaurantRepository.save(restaurant);
        return RestaurantConverter.toDto(saved);
    }

    @Override
    @CacheEvict(value = {"restaurants","allRestaurants"},allEntries = true,key = "#id")
    public RestaurantDto updateRestaurant(Long id, RestaurantDto restaurantDto) {

        Restaurant existing = restaurantRepository.findById(id)
                .orElseThrow(()-> new RestaurantNotFoundException("Restaurant not found whit id:" + id));
         existing.setName(restaurantDto.getName());
         existing.setAddress(restaurantDto.getAddress());
         Restaurant updated = restaurantRepository.save(existing);
         return RestaurantConverter.toDto(updated);



    }
    @Override
    @CacheEvict(value = {"restaurants","allRestaurants"},allEntries = true,key = "#id")
    public void deleteRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(()-> new RestaurantNotFoundException("Restaurant not found" + id));
        restaurantRepository.delete(restaurant);
    }
}
