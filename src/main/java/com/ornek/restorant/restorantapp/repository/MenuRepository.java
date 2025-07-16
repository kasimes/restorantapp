package com.ornek.restorant.restorantapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ornek.restorant.restorantapp.model.entity.Menu;

public interface MenuRepository  extends JpaRepository<Menu,Long> {
}
