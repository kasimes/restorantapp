package com.ornek.restorant.restorantapp.repository;

import com.ornek.restorant.restorantapp.model.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch , Long> {
}
