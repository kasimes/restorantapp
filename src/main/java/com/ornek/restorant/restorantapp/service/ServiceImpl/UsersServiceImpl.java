package com.ornek.restorant.restorantapp.service.ServiceImpl;

import com.ornek.restorant.restorantapp.exception.notfound.CustomerNotFoundException;
import com.ornek.restorant.restorantapp.exception.notfound.NotFoundException;
import com.ornek.restorant.restorantapp.model.converter.UsersConverter;
import com.ornek.restorant.restorantapp.model.dto.UsersDto;
import com.ornek.restorant.restorantapp.model.entity.Address;
import com.ornek.restorant.restorantapp.model.entity.Users;
import com.ornek.restorant.restorantapp.repository.AddressRepository;
import com.ornek.restorant.restorantapp.repository.UsersRepository;
import com.ornek.restorant.restorantapp.service.UsersService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersConverter usersConverter;
    private final AddressRepository addressRepository;

    public UsersServiceImpl(UsersRepository usersRepository, UsersConverter usersConverter, AddressRepository addressRepository) {
        this.usersRepository = usersRepository;
        this.usersConverter = usersConverter;
        this.addressRepository = addressRepository;
    }

    @Override
    @Cacheable(value = {"usersCache"})//bu method ıle cache kaydedilir veriler
    public List<UsersDto> getCustomers() {
        List<Users> users = usersRepository.findAll();
        return users.stream()
                .map(usersConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "usersCache ",key ="#id")//burada id bazlı cache işlemi yapılır
    public UsersDto getCustomerById(long id) {
        Users users = usersRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException("Customer not found with id: " + id));
                return usersConverter.toDto(users);

    }
    @Override
    @CacheEvict(value = "usersCache",allEntries = true)//yeni kullanıcı olusturuldugunda tum kayıtlar temizlenir ve eski veriler kalmaz
    public UsersDto createCustomer(UsersDto usersDto) {
        Address address = addressRepository.findById(usersDto.getAddressId())
                .orElseThrow(() -> new NotFoundException("Address not found with id: " + usersDto.getAddressId()));

        Users users = UsersConverter.toEntity(usersDto,address);
        Users savedUsers = usersRepository.save(users);
        return usersConverter.toDto(savedUsers);
    }

    @Override
    @CacheEvict(value = {"usersChace"},allEntries = true)//cache temizlenir eski veiler kalmaz
    public UsersDto updateCustomer(long id, UsersDto usersDto) {
        Users existingUsers = usersRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException("Customer not found with id: " + id));

        existingUsers.setFirstName(usersDto.getFirstName());
        existingUsers.setLastName(usersDto.getLastName());
        existingUsers.setEmail(usersDto.getEmail());
        existingUsers.setPhoneNumber(usersDto.getPhoneNumber());
        Users updatedUsers = usersRepository.save(existingUsers);
        return usersConverter.toDto(updatedUsers);

    }

    @Override
    @CacheEvict(  value = "usersCache",allEntries = true)//tüm veriler temizlenir
    public void deleteCustomer(long id) {
        Users existingUsers = usersRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException("Customer not found with id: " + id));
        usersRepository.delete(existingUsers);
    }



}
