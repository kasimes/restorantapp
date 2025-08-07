package com.ornek.restorant.restorantapp.controller;


import com.ornek.restorant.restorantapp.model.dto.UsersDto;


import com.ornek.restorant.restorantapp.service.UsersService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerControl {

    private final UsersService usersService;


    public CustomerControl( UsersService usersService) {
        this.usersService = usersService;

    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UsersDto>> getAllCustomers() {
        return ResponseEntity.ok(usersService.getCustomers());
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UsersDto> getCustomerById(@PathVariable long id) {
        return ResponseEntity.ok(usersService.getCustomerById(id));

    }
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UsersDto> createCustomer(@RequestBody UsersDto usersDto) {
        return ResponseEntity.ok(usersService.createCustomer(usersDto));
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UsersDto> updateCustomer(@RequestBody UsersDto usersDto, @PathVariable long id) {
        return ResponseEntity.ok(usersService.updateCustomer(id, usersDto));
    }
    @DeleteMapping("{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long id) {
        usersService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

}
