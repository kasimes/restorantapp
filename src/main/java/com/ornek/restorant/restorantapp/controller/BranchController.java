package com.ornek.restorant.restorantapp.controller;


import com.ornek.restorant.restorantapp.model.dto.BranchDto;
import com.ornek.restorant.restorantapp.service.BranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public List<BranchDto> getAllBranches() {
        return branchService.getAllBranches();
    }

    @GetMapping("/{branchId}")
    @PreAuthorize("hasRole('ADMIN')")
    public BranchDto getBranchById(@PathVariable Long branchId) {
        return branchService.getBranchById(branchId);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/nearby")
    public List<BranchDto> getNearbyBranches(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam (defaultValue = "10") Double radius) {
        return branchService.getNearbyBranches(latitude,longitude,radius);

    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public BranchDto createBranch(@RequestBody BranchDto branchDto) {
        return branchService.saveBranch(branchDto);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public BranchDto updateBranch( @PathVariable Long id,@RequestBody BranchDto branchDto) {
        return branchService.updateBranch(id,branchDto);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }



}
