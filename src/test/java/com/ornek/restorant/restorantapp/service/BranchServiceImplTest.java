package com.ornek.restorant.restorantapp.service;

import com.ornek.restorant.restorantapp.exception.notfound.BranchNotFoundException;
import com.ornek.restorant.restorantapp.model.converter.BranchConverter;
import com.ornek.restorant.restorantapp.model.dto.BranchDto;
import com.ornek.restorant.restorantapp.model.entity.Branch;
import com.ornek.restorant.restorantapp.model.entity.Restaurant;
import com.ornek.restorant.restorantapp.repository.BranchRepository;
import com.ornek.restorant.restorantapp.repository.RestaurantRepository;
import com.ornek.restorant.restorantapp.service.ServiceImpl.BranchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.util.List;
import java.util.Optional;

public class BranchServiceImplTest {

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private BranchConverter branchConverter;

    @Mock
    private RestaurantRepository restaurantRepository;

    private Restaurant restaurant;

    @InjectMocks
    private BranchServiceImpl branchServiceImpl;

    private Branch branch;
    private BranchDto  branchDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        restaurant = new Restaurant();
        restaurant.setId(1L);

         branch = new Branch();
        branch.setId(1L);
        branch.setName("Test Branch");
        branch.setRestaurant(restaurant);

        branchDto = new BranchDto();
        branchDto.setId(1L);
        branchDto.setName("Test Branch");
        branchDto.setRestaurantId(1L);


    }

    @Test

    void testGetBranchById() {
        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));
       when(branchConverter.toDto(branch)).thenReturn(branchDto);

        BranchDto branchDto = branchServiceImpl.getBranchById(1L);

        assertNotNull(branchDto);
        assertEquals("Test Branch", branchDto.getName());
        verify(branchRepository,times(1)).findById(1l);

    }

    @Test
    void testGetBranchByIdNotFound() {
        when(branchRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(BranchNotFoundException.class, () -> branchServiceImpl.getBranchById(1L));
        verify(branchRepository,times(1)).findById(1L);
    }

    @Test
    void testGetAllBranches() {
        when(branchRepository.findAll()).thenReturn(List.of(new Branch()));
        when(branchConverter.toDto(branch)).thenReturn(branchDto);
        List<BranchDto> branchDtos = branchServiceImpl.getAllBranches();
        assertEquals(1,branchDtos.size());
        verify(branchRepository,times(1)).findAll();
    }

    @Test
    void testSaveBranch() {

        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));
        when(branchConverter.toDto(any(Branch.class))).thenReturn(branchDto);
        when(branchRepository.save(any(Branch.class))).thenReturn(branch);


        BranchDto branchDto1 = branchServiceImpl.saveBranch(branchDto);

        assertNotNull(branchDto1);
        assertEquals("Test Branch", branchDto1.getName());
        verify(branchRepository,times(1)).save(any(Branch.class));
    }

    @Test
    void testUpdateBranch() {
        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));
        when(branchConverter.toDto(branch)).thenReturn(branchDto);
        when(branchRepository.save(any(Branch.class))).thenReturn(branch);

        BranchDto branchDto2 = branchServiceImpl.updateBranch(1L,branchDto);

        assertNotNull(branchDto2);
        assertEquals("Test Branch", branchDto2.getName());
        verify(branchRepository,times(1)).save(branch);
    }

    @Test
    void testDeleteBranch() {
        doNothing().when(branchRepository).deleteById(1L);
        branchServiceImpl.deleteBranch(1L);
        verify(branchRepository,times(1)).deleteById(1L);


    }




}
