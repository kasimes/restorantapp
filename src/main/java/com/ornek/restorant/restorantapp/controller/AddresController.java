package com.ornek.restorant.restorantapp.controller;


import com.ornek.restorant.restorantapp.model.dto.AdressDTO;
import com.ornek.restorant.restorantapp.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddresController {

    private final AddressService addressService;

    public AddresController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public AdressDTO createAddress(@RequestBody AdressDTO addressDto) {
        return addressService.createAddress(addressDto);
    }

    @GetMapping("/{id}")
    public AdressDTO getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }



    @GetMapping
    public List<AdressDTO> getAllAddresses() {
        return addressService.getAllAddresses();
    }


    @PutMapping("/{id}")
    public AdressDTO updateAddress(@PathVariable Long id, @RequestBody AdressDTO addressDto) {
        return addressService.updateAddress(id, addressDto);
    }

    // Adres silme
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }



}
