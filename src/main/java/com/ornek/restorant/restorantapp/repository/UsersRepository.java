package com.ornek.restorant.restorantapp.repository;

import com.ornek.restorant.restorantapp.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);

}
