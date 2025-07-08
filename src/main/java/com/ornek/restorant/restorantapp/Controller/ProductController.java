package com.ornek.restorant.restorantapp.Controller;

import com.ornek.restorant.restorantapp.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ornek.restorant.restorantapp.service.ProductService;
import com.ornek.restorant.restorantapp.entity.Product;



import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> listAllProducts() {
        return productService.findAllProduct();
    }

}

