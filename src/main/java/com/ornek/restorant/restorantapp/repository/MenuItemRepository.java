package com.ornek.restorant.restorantapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ornek.restorant.restorantapp.entity.Menuitem;

public interface MenuItemRepository extends JpaRepository<Menuitem,Long> {
}
