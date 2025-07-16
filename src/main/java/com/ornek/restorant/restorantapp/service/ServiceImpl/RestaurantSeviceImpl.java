package com.ornek.restorant.restorantapp.service.ServiceImpl;

import com.ornek.restorant.restorantapp.model.dto.RestaurantDto;
import com.ornek.restorant.restorantapp.model.entity.Restaurant;
import com.ornek.restorant.restorantapp.repository.RestaurantRepository;
import com.ornek.restorant.restorantapp.service.RestaurantService;
import com.ornek.restorant.restorantapp.model.converter.RestaurantConverter;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantSeviceImpl implements RestaurantService {

    private final RestaurantRepository  restaurantRepository;

    public RestaurantSeviceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<RestaurantDto> getAllRestaurants() {

        return restaurantRepository.findAll()
                .stream()
                .map(RestaurantConverter::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public RestaurantDto getRestaurantById(Long id) {
        Restaurant  restaurant = restaurantRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Restaurant not found"));
        return RestaurantConverter.toDto(restaurant);

    }

    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = RestaurantConverter.toEntity(restaurantDto);
        Restaurant saved = restaurantRepository.save(restaurant);
        return RestaurantConverter.toDto(saved);
    }

    @Override
    public RestaurantDto updateRestaurant(Long id, RestaurantDto restaurantDto) {

        Restaurant existing = restaurantRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Restaurant not found whit id:" + id));
         existing.setName(restaurantDto.getName());
         existing.setAddress(restaurantDto.getAddress());
         Restaurant updated = restaurantRepository.save(existing);
         return RestaurantConverter.toDto(updated);



    }
    @Override
    public void deleteRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Restaurant not found" + id));
        restaurantRepository.delete(restaurant);
    }
}
