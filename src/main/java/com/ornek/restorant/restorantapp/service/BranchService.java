package com.ornek.restorant.restorantapp.service;

import com.ornek.restorant.restorantapp.model.dto.BranchDto;
import com.ornek.restorant.restorantapp.model.entity.Branch;

import java.util.List;

public interface BranchService {
    List<BranchDto> getAllBranches();
    BranchDto getBranchById(Long branchId);
    BranchDto saveBranch(BranchDto branchDto);
    BranchDto updateBranch(Long branchId, BranchDto branchDto);
    void deleteBranch(Long branchId);

}
