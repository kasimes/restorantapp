package com.ornek.restorant.restorantapp.service;

import com.ornek.restorant.restorantapp.model.dto.AdressDTO;


import java.util.List;

public interface AddressService {


    AdressDTO createAddress(AdressDTO addressDto);

    AdressDTO getAddressById(Long id);

    List<AdressDTO> getAllAddresses();

    AdressDTO updateAddress(Long id, AdressDTO addressDto);

    void deleteAddress(Long id);

}
