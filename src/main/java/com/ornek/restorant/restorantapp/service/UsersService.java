package com.ornek.restorant.restorantapp.service;

import com.ornek.restorant.restorantapp.model.dto.UsersDto;


import java.util.List;

public interface UsersService {
    List<UsersDto> getCustomers();

    UsersDto getCustomerById(long id);

    UsersDto createCustomer(UsersDto usersDto);

    UsersDto updateCustomer(long id, UsersDto usersDto);

    void deleteCustomer(long id);

}
