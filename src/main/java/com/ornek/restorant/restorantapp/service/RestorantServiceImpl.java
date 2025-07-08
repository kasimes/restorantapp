package com.ornek.restorant.restorantapp.service;

import com.ornek.restorant.restorantapp.entity.Restaurant;
import com.ornek.restorant.restorantapp.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestorantServiceImpl implements RestorantService {

    private final RestaurantRepository restaurantRepository;

    public RestorantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }


    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
    }

    @Override
    public Restaurant update(Long id, Restaurant updatedRestaurant) {
        Restaurant existing = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));

        existing.setName(updatedRestaurant.getName());
        existing.setAddress(updatedRestaurant.getAddress());
        // Diğer alanlar varsa onları da güncelle

        return restaurantRepository.save(existing);
    }
}
