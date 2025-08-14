package com.ornek.restorant.restorantapp.service.ServiceImpl;

import com.ornek.restorant.restorantapp.exception.notfound.NotFoundException;
import com.ornek.restorant.restorantapp.model.converter.AddressConverter;
import com.ornek.restorant.restorantapp.model.dto.AdressDTO;
import com.ornek.restorant.restorantapp.model.entity.Address;
import com.ornek.restorant.restorantapp.repository.AddressRepository;
import com.ornek.restorant.restorantapp.service.AddressService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository  addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public AdressDTO createAddress(AdressDTO addressDto) {
        Address address = AddressConverter.toEntity(addressDto);
        Address savedAddress = addressRepository.save(address);
        return AddressConverter.toDto(savedAddress);

    }

    @Override
    public AdressDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Address not found"));
        return AddressConverter.toDto(address);
    }

    @Override
    public List<AdressDTO> getAllAddresses() {
        return addressRepository.findAll().stream()
                .map(AddressConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdressDTO updateAddress(Long id, AdressDTO addressDto) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));


        existingAddress.setCity(addressDto.getCity());
        existingAddress.setDistrict(addressDto.getDistrict());
        existingAddress.setFullAddress(addressDto.getFullAddress());
        existingAddress.setLatitude(addressDto.getLatitude());
        existingAddress.setLongitude(addressDto.getLongitude());

        Address updated = addressRepository.save(existingAddress);
        return AddressConverter.toDto(updated);

    }

    @Override
    public void deleteAddress(Long id) {
        Address existing = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address not found with id: " + id));
        addressRepository.delete(existing);

    }
}
