package com.ornek.restorant.restorantapp.controller;


import com.ornek.restorant.restorantapp.model.dto.MenuDto;
import com.ornek.restorant.restorantapp.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<List<MenuDto>> getAllMenus() {
        List<MenuDto> menus = menuService.getAllMenus();
        return  ResponseEntity.ok(menus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> getMenuById(@PathVariable Long id) {
        MenuDto menuDto = menuService.getMenuById(id);
        return  ResponseEntity.ok(menuDto);
    }

    @PostMapping
    public ResponseEntity<MenuDto> createMenu(@RequestBody MenuDto menuDto) {
        MenuDto createdMenuDto = menuService.createMenu(menuDto);
        return  ResponseEntity.ok(createdMenuDto);
    }
    @PutMapping
    public ResponseEntity<MenuDto> updateMenu(@PathVariable Long id ,@RequestBody MenuDto menuDto) {
        menuDto.setId(id);
        MenuDto updatedMenuDto = menuService.updateMenu(menuDto);
        return  ResponseEntity.ok(updatedMenuDto);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MenuDto> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return  ResponseEntity.noContent().build();
    }

}
