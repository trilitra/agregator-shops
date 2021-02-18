package com.example.app.repository;

import com.example.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepo extends JpaRepository<Product,Integer> {

   Product findProductByProductId(Integer productId);
   List<Product> findByProductNameContainingIgnoreCase(String name);
   List <Product> findAProductByCodeContains (String code);

}
