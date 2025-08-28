package com.phoenix.simpleProductWebApp.service;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import com.phoenix.simpleProductWebApp.model.Product;
import com.phoenix.simpleProductWebApp.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts(){
        return repo.findAll();
    }

    public Product getProductById(int id){
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product prod){
        return repo.save(prod);
    }

    public Product updateProduct(Product prod){
        return repo.save(prod);
    }

    public void deleteProduct(int id){
        repo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword){
        return repo.searchProducts(keyword);
    }
}
