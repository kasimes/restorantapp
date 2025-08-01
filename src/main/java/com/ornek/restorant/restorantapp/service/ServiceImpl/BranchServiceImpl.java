package com.ornek.restorant.restorantapp.service.ServiceImpl;

import com.ornek.restorant.restorantapp.exception.notfound.BranchNotFoundException;
import com.ornek.restorant.restorantapp.exception.notfound.RestaurantNotFoundException;
import com.ornek.restorant.restorantapp.model.converter.BranchConverter;
import com.ornek.restorant.restorantapp.model.dto.BranchDto;
import com.ornek.restorant.restorantapp.model.entity.Address;
import com.ornek.restorant.restorantapp.model.entity.Branch;
import com.ornek.restorant.restorantapp.repository.AddressRepository;
import com.ornek.restorant.restorantapp.repository.BranchRepository;
import com.ornek.restorant.restorantapp.repository.RestaurantRepository;
import com.ornek.restorant.restorantapp.service.BranchService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService{
    private final BranchRepository branchRepository;
    private final RestaurantRepository restaurantRepository;
    private final BranchConverter branchConverter ;
    private final AddressRepository  addressRepository;

    public BranchServiceImpl(BranchRepository branchRepository, RestaurantRepository restaurantRepository, BranchConverter branchConverter, AddressRepository addressRepository) {
        this.branchRepository = branchRepository;
        this.restaurantRepository = restaurantRepository;
        this.branchConverter = branchConverter;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<BranchDto> getNearbyBranches(double latitude, double longitude, double radiusKM) {
            List<Branch> branches = branchRepository.findBranchesNearby(latitude, longitude, radiusKM);
            return branches.stream()
                    .map(branchConverter::toDto)
                    .toList();
    }

    @Override
    @Cacheable(value = "brancheCache")//veriler cache de tutulur
    public List<BranchDto> getAllBranches(){
        List<Branch> branches = branchRepository.findAll();
        return branches.stream()
                .map(branchConverter::toDto)
                .toList();
    }

    @Override
    @Cacheable(value = "brancheCache",key = "#branchId")//ıd ye gore cache alırnı
    public BranchDto getBranchById(Long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new BranchNotFoundException("Branch not found with id: " + branchId));
        return branchConverter.toDto(branch);
    }

    @Override
    @CacheEvict(value = "brancheCache",allEntries = true)//guncellme yapılır
    public BranchDto saveBranch(BranchDto branchDto) {
        var restaurant = restaurantRepository.findById(branchDto.getRestaurantId())
                .orElseThrow(()-> new RestaurantNotFoundException(
                        "Restaurant not found with id: " + branchDto.getRestaurantId()
                ));

        Address address = addressRepository.findById(branchDto.getAddressId())
                .orElseThrow(()-> new RuntimeException("Address not found with id: " + branchDto.getAddressId()));
        Branch branch = BranchConverter.toEntity(branchDto , address);

        branch.setRestaurant(restaurant);
        Branch savedBranch = branchRepository.save(branch);
        return branchConverter.toDto(savedBranch);
    }

    @Override
    @CacheEvict(value = "brancheCache",allEntries = true)//guncellme yapılır
    public BranchDto updateBranch(Long branchId, BranchDto branchDto) {
        Branch existingBranch = branchRepository.findById(branchId)
                .orElseThrow(() -> new BranchNotFoundException("Branch not found with id: " + branchId));

        existingBranch.setName(branchDto.getName());
        // Restaurant güncellemesi gerekiyorsa buraya ekle

        Branch updatedBranch = branchRepository.save(existingBranch);
        return branchConverter.toDto(updatedBranch);
    }

    @Override
    @CacheEvict(value = "BrancheCache",allEntries = true)//guncellme yapılır
    public void deleteBranch(Long branchId) {
        branchRepository.deleteById(branchId);

    }

}
