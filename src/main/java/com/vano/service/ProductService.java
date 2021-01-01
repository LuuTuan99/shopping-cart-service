package com.vano.service;

import com.vano.entity.Products;
import com.vano.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductsRepository productsRepository;

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }


}
