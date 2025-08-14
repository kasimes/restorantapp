package com.ornek.restorant.restorantapp.model.converter;

import com.ornek.restorant.restorantapp.model.dto.AdressDTO;
import com.ornek.restorant.restorantapp.model.entity.Address;

public class AddressConverter {

    // DTO → Entity
    public static Address toEntity(AdressDTO dto) {
        if (dto == null) return null;

        Address address = new Address();
        address.setId(dto.getId());
        address.setCity(dto.getCity());
        address.setDistrict(dto.getDistrict());
        address.setFullAddress(dto.getFullAddress());
        address.setLatitude(dto.getLatitude());
        address.setLongitude(dto.getLongitude());

        return address;
    }

    // Entity → DTO
    public static AdressDTO toDto(Address address) {
        if (address == null) return null;

        AdressDTO dto = new AdressDTO();
        dto.setId(address.getId());
        dto.setCity(address.getCity());
        dto.setDistrict(address.getDistrict());
        dto.setFullAddress(address.getFullAddress());
        dto.setLatitude(address.getLatitude());
        dto.setLongitude(address.getLongitude());

        return dto;
    }
}
