package com.ornek.restorant.restorantapp.Controller;

import com.ornek.restorant.restorantapp.entity.Restaurant;
import org.springframework.web.bind.annotation.*;
//import com.ornek.restorant.restorantapp.repository.RestaurantRepository;
import com.ornek.restorant.restorantapp.service.RestorantService;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestorantController {



        private final RestorantService restorantService;


        public RestorantController(RestorantService restorantService) {
            this.restorantService = restorantService;
        }

        @PostMapping
        public Restaurant create(@RequestBody Restaurant restaurant) {
            return restorantService.save(restaurant);
        }

        @GetMapping
        public List<Restaurant> getAll() {
            return restorantService.findAll();
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
            restorantService.delete(id);
        }

    @GetMapping("/{id}")
    public Restaurant getById(@PathVariable Long id) {
        return restorantService.findById(id);
    }

    @PutMapping("/{id}")
    public Restaurant update(@PathVariable Long id, @RequestBody Restaurant updatedRestaurant) {
        return restorantService.update(id, updatedRestaurant);
    }
}
