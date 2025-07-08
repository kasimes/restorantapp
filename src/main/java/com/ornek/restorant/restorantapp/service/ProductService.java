package com.ornek.restorant.restorantapp.service;

import  com.ornek.restorant.restorantapp.entity.Product;
import com.ornek.restorant.restorantapp.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }



}
