package com.ornek.restorant.restorantapp.model.converter;

import com.ornek.restorant.restorantapp.model.entity.Customer;
import com.ornek.restorant.restorantapp.model.dto.CustomerDto;
import org.springframework.stereotype.Component;


@Component
public class CustomerConverter {

    public  CustomerDto toDto(Customer customer)
    {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNumber());
        return dto;
    }

    public static Customer toEntity(CustomerDto customerDto)
    {
        if (customerDto == null) return null;
        Customer entity = new Customer();
        entity.setId(customerDto.getId());
        entity.setFirstName(customerDto.getFirstName());
        entity.setLastName(customerDto.getLastName());
        entity.setEmail(customerDto.getEmail());
        entity.setPhoneNumber(customerDto.getPhoneNumber());
        return entity;
    }

}
