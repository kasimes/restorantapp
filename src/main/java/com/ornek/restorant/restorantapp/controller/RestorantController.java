package com.ornek.restorant.restorantapp.controller;

import com.ornek.restorant.restorantapp.model.RestaurantDto;
import com.ornek.restorant.restorantapp.service.RestorantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restorant")
public class RestorantController {

    private final RestorantService restorantService;

    public RestorantController(RestorantService restorantService) {
        this.restorantService = restorantService;
    }

    @PostMapping
    public RestaurantDto create(@RequestBody RestaurantDto restaurantDto) {
        return restorantService.save(restaurantDto);
    }

    @GetMapping
    public List<RestaurantDto> getAll() {
        return restorantService.findAll();
    }

    @GetMapping("/{id}")
    public RestaurantDto getById(@PathVariable Long id) {
        return restorantService.findById(id);
    }

    @PutMapping("/{id}")
    public RestaurantDto update(@PathVariable Long id, @RequestBody RestaurantDto restaurantDto) {
        return restorantService.update(id, restaurantDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        restorantService.delete(id);
    }
}
