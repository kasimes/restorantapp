package com.ornek.restorant.restorantapp.model.converter;

import com.ornek.restorant.restorantapp.model.dto.BranchDto;
import com.ornek.restorant.restorantapp.model.entity.Branch;
import com.ornek.restorant.restorantapp.model.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class BranchConverter {

    public BranchDto toDto(Branch branch) {

        if(branch == null) return null;

        BranchDto dto = new BranchDto();
        dto.setId(branch.getId());
        dto.setName(branch.getName());
        dto.setRestaurantId(branch.getRestaurant().getId());
        return dto;

    }

    public static Branch toEntity(BranchDto dto) {
        if(dto == null) return null;
        Branch branch = new Branch();
        branch.setId(dto.getId());
        branch.setName(dto.getName());


        return branch;
    }

}
