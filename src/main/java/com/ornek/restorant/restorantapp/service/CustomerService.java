package com.ornek.restorant.restorantapp.service;

import com.ornek.restorant.restorantapp.model.dto.CustomerDto;


import java.util.List;

public interface CustomerService {
    List<CustomerDto> getCustomers();
    CustomerDto getCustomerById(long id);
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(long id, CustomerDto customerDto);
    void deleteCustomer(long id);

}
