package com.ornek.restorant.restorantapp.repository;

import com.ornek.restorant.restorantapp.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UsersRepository extends JpaRepository<Users,Long> {
}
