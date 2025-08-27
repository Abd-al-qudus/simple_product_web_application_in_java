package com.phoenix.simpleProductWebApp.controller;

import java.util.List;
import com.phoenix.simpleProductWebApp.model.Product;
import com.phoenix.simpleProductWebApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    ProductService services;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = services.getAllProducts();
        if(!products.isEmpty())
            return new ResponseEntity<>(products, HttpStatus.OK);
        else
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product product = services.getProductById(id);
        if(product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestBody Product prod) {
        try{
            Product prodSnapShot = services.addProduct(prod);
            return new ResponseEntity<>(prodSnapShot, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/products")
    public ResponseEntity<?> updateProduct(@RequestBody Product prod){
        try{
            Product prodSnapShot = services.updateProduct(prod);
            return new ResponseEntity<Product>(prodSnapShot, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        Product prod = services.getProductById(id);
        if(prod != null){
            services.deleteProduct(id);
            return new ResponseEntity<>("deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("product does not exist", HttpStatus.NOT_FOUND);
    }
}
