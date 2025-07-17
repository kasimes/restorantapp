package com.ornek.restorant.restorantapp.model.converter;

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
        return dto;
    }

    public static Users toEntity(UsersDto usersDto)
    {
        if (usersDto == null) return null;
        Users entity = new Users();
        entity.setId(usersDto.getId());
        entity.setFirstName(usersDto.getFirstName());
        entity.setLastName(usersDto.getLastName());
        entity.setEmail(usersDto.getEmail());
        entity.setPhoneNumber(usersDto.getPhoneNumber());
        return entity;
    }

}
