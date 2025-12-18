package com.conglt.learning.springbootboilerplate.service;

import com.conglt.learning.springbootboilerplate.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    List<Product> getProductsByCategoryId(Long categoryId);
}
