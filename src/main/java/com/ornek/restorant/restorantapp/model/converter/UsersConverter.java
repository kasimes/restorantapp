package com.ornek.restorant.restorantapp.model.converter;

import com.ornek.restorant.restorantapp.model.entity.Users;
import com.ornek.restorant.restorantapp.model.dto.UsersDto;
import org.springframework.stereotype.Component;


@Component
public class UsersConverter {

    // DTO → Entity
    public static Users toEntity(UsersDto dto) {
        if (dto == null) return null;

        Users users = new Users();
        users.setId(dto.getId());
        users.setFirstName(dto.getFirstName());
        users.setLastName(dto.getLastName());
        users.setEmail(dto.getEmail());
        users.setPhoneNumber(dto.getPhoneNumber());
        users.setPassword(dto.getPassword());
        users.setRole(dto.getRole());

        // Address dönüştürme ayrı converter ile
        if (dto.getAddressDTO() != null) {
            users.setAddress(AddressConverter.toEntity(dto.getAddressDTO()));
        }

        return users;
    }

    // Entity → DTO
    public UsersDto toDto(Users entity) {
        if (entity == null) return null;

        UsersDto dto = new UsersDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());

        if (entity.getAddress() != null) {
            dto.setAddressDTO(AddressConverter.toDto(entity.getAddress()));
        }

        return dto;
    }

}
