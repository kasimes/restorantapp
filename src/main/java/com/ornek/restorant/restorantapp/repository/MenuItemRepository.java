package com.ornek.restorant.restorantapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ornek.restorant.restorantapp.model.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {
}
