package com.ornek.restorant.restorantapp.controller;


import com.ornek.restorant.restorantapp.model.dto.UsersDto;


import com.ornek.restorant.restorantapp.service.UsersService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerControl {

    private final UsersService usersService;


    public CustomerControl( UsersService usersService) {
        this.usersService = usersService;

    }
    @GetMapping
    public ResponseEntity<List<UsersDto>> getAllCustomers() {
        return ResponseEntity.ok(usersService.getCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDto> getCustomerById(@PathVariable long id) {
        return ResponseEntity.ok(usersService.getCustomerById(id));

    }
    @PostMapping
    public ResponseEntity<UsersDto> createCustomer(@RequestBody UsersDto usersDto) {
        return ResponseEntity.ok(usersService.createCustomer(usersDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsersDto> updateCustomer(@RequestBody UsersDto usersDto, @PathVariable long id) {
        return ResponseEntity.ok(usersService.updateCustomer(id, usersDto));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<UsersDto> deleteCustomer(@PathVariable long id) {
        usersService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

}
