package com.ornek.restorant.restorantapp.repository;

import com.ornek.restorant.restorantapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
