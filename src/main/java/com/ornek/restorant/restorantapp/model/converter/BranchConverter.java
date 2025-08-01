package com.ornek.restorant.restorantapp.model.converter;

import com.ornek.restorant.restorantapp.model.dto.BranchDto;
import com.ornek.restorant.restorantapp.model.entity.Address;
import com.ornek.restorant.restorantapp.model.entity.Branch;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class BranchConverter {

    public BranchDto toDto(Branch branch) {

        if(branch == null) return null;

        BranchDto dto = new BranchDto();
        dto.setId(branch.getId());
        dto.setName(branch.getName());
        dto.setRestaurantId(branch.getRestaurant().getId());
        dto.setMinimumOrderAmount(branch.getMinimumOrderAmount());

        dto.setOpeningTime(branch.getOpeningTime()!=null?branch.getOpeningTime().toString():null);
        dto.setClosingTime(branch.getClosingTime()!=null?branch.getClosingTime().toString():null);

        if (branch.getAddress() != null) {
            dto.setCity(branch.getAddress().getCity());
            dto.setDistrict(branch.getAddress().getDistrict());
            dto.setFullAddress(branch.getAddress().getFullAddress());
            dto.setLatitude(branch.getAddress().getLatitude());
            dto.setLongitude(branch.getAddress().getLongitude());
        }
        return dto;

    }

    public static Branch toEntity(BranchDto dto , Address address) {
        if(dto == null) return null;
        Branch branch = new Branch();
        branch.setId(dto.getId());
        branch.setName(dto.getName());

        branch.setAddress(address);
        branch.setMinimumOrderAmount(dto.getMinimumOrderAmount());

        branch.setOpeningTime(dto.getOpeningTime() != null ? LocalTime.parse(dto.getOpeningTime()) : null);
        branch.setClosingTime(dto.getClosingTime() != null ? LocalTime.parse(dto.getClosingTime()) : null);
        return branch;
    }

}
