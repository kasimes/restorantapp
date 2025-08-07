package com.ornek.restorant.restorantapp.model.converter;

import com.ornek.restorant.restorantapp.model.entity.Address;
import com.ornek.restorant.restorantapp.model.entity.Users;
import com.ornek.restorant.restorantapp.model.dto.UsersDto;
import org.springframework.stereotype.Component;


@Component
public class UsersConverter {

    public UsersDto toDto(Users users)
    {
        UsersDto dto = new UsersDto();
        dto.setId(users.getId());
        dto.setFirstName(users.getFirstName());
        dto.setLastName(users.getLastName());
        dto.setEmail(users.getEmail());
        dto.setPhoneNumber(users.getPhoneNumber());
        dto.setRole(users.getRole());



        if (users.getAddress() != null) {
            dto.setCity(users.getAddress().getCity());
            dto.setDistrict(users.getAddress().getDistrict());
            dto.setFullAddress(users.getAddress().getFullAddress());
            dto.setLatitude(users.getAddress().getLatitude());
            dto.setLongitude(users.getAddress().getLongitude());
        }
        return dto;
    }

    public static Users toEntity(UsersDto usersDto , Address address)
    {
        if (usersDto == null) return null;
        Users entity = new Users();
        entity.setId(usersDto.getId());
        entity.setFirstName(usersDto.getFirstName());
        entity.setLastName(usersDto.getLastName());
        entity.setEmail(usersDto.getEmail());
        entity.setPhoneNumber(usersDto.getPhoneNumber());
        entity.setAddress(address);
        entity.setPassword(usersDto.getPassword());
        entity.setRole(usersDto.getRole());// dışarıdan sağlanan Address entity
        return entity;
    }

}
