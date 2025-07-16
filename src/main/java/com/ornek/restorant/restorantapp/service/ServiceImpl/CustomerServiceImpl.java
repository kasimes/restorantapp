package com.ornek.restorant.restorantapp.service.ServiceImpl;

import com.ornek.restorant.restorantapp.model.converter.CustomerConverter;
import com.ornek.restorant.restorantapp.model.dto.CustomerDto;
import com.ornek.restorant.restorantapp.model.entity.Customer;
import com.ornek.restorant.restorantapp.repository.CustomerRepository;
import com.ornek.restorant.restorantapp.service.CustomerService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerConverter customerConverter) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
    }

    @Override
    public List<CustomerDto> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Customer not found with id: " + id));
                return customerConverter.toDto(customer);

    }
    @Override
    public  CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = CustomerConverter.toEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerConverter.toDto(savedCustomer);
    }

    @Override
    public  CustomerDto updateCustomer(long id, CustomerDto customerDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Customer not found with id: " + id));

        existingCustomer.setFirstName(customerDto.getFirstName());
        existingCustomer.setLastName(customerDto.getLastName());
        existingCustomer.setEmail(customerDto.getEmail());
        existingCustomer.setPhoneNumber(customerDto.getPhoneNumber());
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return customerConverter.toDto(updatedCustomer);

    }

    @Override
    public void deleteCustomer(long id) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Customer not found with id: " + id));
        customerRepository.delete(existingCustomer);
    }



}
