package com.ornek.restorant.restorantapp.service.ServiceImpl;

import com.ornek.restorant.restorantapp.model.converter.UsersConverter;
import com.ornek.restorant.restorantapp.model.dto.UsersDto;
import com.ornek.restorant.restorantapp.model.entity.Users;
import com.ornek.restorant.restorantapp.repository.UsersRepository;
import com.ornek.restorant.restorantapp.service.UsersService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersConverter usersConverter;
    
    public UsersServiceImpl(UsersRepository usersRepository, UsersConverter usersConverter) {
        this.usersRepository = usersRepository;
        this.usersConverter = usersConverter;
    }

    @Override
    public List<UsersDto> getCustomers() {
        List<Users> users = usersRepository.findAll();
        return users.stream()
                .map(usersConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsersDto getCustomerById(long id) {
        Users users = usersRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Customer not found with id: " + id));
                return usersConverter.toDto(users);

    }
    @Override
    public UsersDto createCustomer(UsersDto usersDto) {
        Users users = UsersConverter.toEntity(usersDto);
        Users savedUsers = usersRepository.save(users);
        return usersConverter.toDto(savedUsers);
    }

    @Override
    public UsersDto updateCustomer(long id, UsersDto usersDto) {
        Users existingUsers = usersRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Customer not found with id: " + id));

        existingUsers.setFirstName(usersDto.getFirstName());
        existingUsers.setLastName(usersDto.getLastName());
        existingUsers.setEmail(usersDto.getEmail());
        existingUsers.setPhoneNumber(usersDto.getPhoneNumber());
        Users updatedUsers = usersRepository.save(existingUsers);
        return usersConverter.toDto(updatedUsers);

    }

    @Override
    public void deleteCustomer(long id) {
        Users existingUsers = usersRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Customer not found with id: " + id));
        usersRepository.delete(existingUsers);
    }



}
