package com.conglt.learning.springbootboilerplate.repository;

import com.conglt.learning.springbootboilerplate.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
}
