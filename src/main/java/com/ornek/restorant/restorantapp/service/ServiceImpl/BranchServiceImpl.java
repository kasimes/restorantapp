package com.ornek.restorant.restorantapp.service.ServiceImpl;

import com.ornek.restorant.restorantapp.model.converter.BranchConverter;
import com.ornek.restorant.restorantapp.model.dto.BranchDto;
import com.ornek.restorant.restorantapp.model.entity.Branch;
import com.ornek.restorant.restorantapp.repository.BranchRepository;
import com.ornek.restorant.restorantapp.service.BranchService;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService{
    private final BranchRepository branchRepository;
    private final BranchConverter branchConverter ;

    public BranchServiceImpl(BranchRepository branchRepository, BranchConverter branchConverter) {
        this.branchRepository = branchRepository;
        this.branchConverter = branchConverter;
    }

    @Override
    public List<BranchDto> getAllBranches(){
        List<Branch> branches = branchRepository.findAll();
        return branches.stream()
                .map(branchConverter::toDto)
                .toList();
    }

    @Override
    public BranchDto getBranchById(Long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + branchId));
        return branchConverter.toDto(branch);
    }

    @Override
    public BranchDto saveBranch(BranchDto branchDto) {

        Branch branch = branchConverter.toEntity(branchDto);

        Branch savedBranch = branchRepository.save(branch);
        return branchConverter.toDto(savedBranch);
    }

    @Override
    public BranchDto updateBranch(Long branchId, BranchDto branchDto) {
        Branch existingBranch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + branchId));

        existingBranch.setName(branchDto.getName());
        // Restaurant güncellemesi gerekiyorsa buraya ekle

        Branch updatedBranch = branchRepository.save(existingBranch);
        return branchConverter.toDto(updatedBranch);
    }

    @Override
    public void deleteBranch(Long branchId) {
        branchRepository.deleteById(branchId);

    }

}
