package com.ornek.restorant.restorantapp.controller;


import com.ornek.restorant.restorantapp.model.dto.CustomerDto;


import com.ornek.restorant.restorantapp.service.CustomerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerControl {

    private final CustomerService customerService;


    public CustomerControl( CustomerService customerService) {
        this.customerService = customerService;

    }
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));

    }
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.createCustomer(customerDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable long id) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerDto));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

}
