package com.ornek.restorant.restorantapp.controller;


import com.ornek.restorant.restorantapp.model.dto.BranchDto;
import com.ornek.restorant.restorantapp.service.BranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping
    public List<BranchDto> getAllBranches() {
        return branchService.getAllBranches();
    }

    @GetMapping("/{branchId}")
    public BranchDto getBranchById(@PathVariable Long branchId) {
        return branchService.getBranchById(branchId);
    }
    @PostMapping
    public BranchDto createBranch(@RequestBody BranchDto branchDto) {
        return branchService.saveBranch(branchDto);
    }
    @PutMapping("/{id}")
    public BranchDto updateBranch( @PathVariable Long id,@RequestBody BranchDto branchDto) {
        return branchService.updateBranch(id,branchDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }



}
