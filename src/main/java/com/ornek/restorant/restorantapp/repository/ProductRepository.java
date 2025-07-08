package com.ornek.restorant.restorantapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ornek.restorant.restorantapp.entity.Product;



public interface ProductRepository extends JpaRepository<Product, Long> { }