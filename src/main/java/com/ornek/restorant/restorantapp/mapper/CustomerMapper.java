package com.ornek.restorant.restorantapp.mapper;

import com.ornek.restorant.restorantapp.model.CustomerDto;
import com.ornek.restorant.restorantapp.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);

    Customer toEntity(CustomerDto customerDto);
}
