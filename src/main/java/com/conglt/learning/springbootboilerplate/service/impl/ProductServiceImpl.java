package com.conglt.learning.springbootboilerplate.service.impl;

import com.conglt.learning.springbootboilerplate.model.Category;
import com.conglt.learning.springbootboilerplate.model.Product;
import com.conglt.learning.springbootboilerplate.repository.CategoryRepository;
import com.conglt.learning.springbootboilerplate.repository.ProductRepository;
import com.conglt.learning.springbootboilerplate.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product createProduct(Product product) {
        //neu product gui len co category thi minh phai kiem tra xem category do co ton tai hay khong
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            //tim category trong database
            Category category = categoryRepository.findById(product.getCategory().getId()).orElseThrow(() -> new RuntimeException("Catefory Id not found" + product.getCategory().getId()));

            //gan category tim duoc vao product
            product.setCategory(category);
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        //tim xem san pham cu co ton tai hay khong
        Product existingProduct = getProductById(id);

        //cap nhat thong tin moi vao san pham cu
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        existingProduct.setImageUrl(product.getImageUrl());

        if (product.getCategory() != null && product.getCategory().getId() != null) {
            Category category = categoryRepository.findById(product.getCategory().getId()).orElseThrow(() -> new RuntimeException("Category not found with id: " + product.getCategory().getId()));
            existingProduct.setCategory(category);
        }
        //luu lai
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        //truoc khi xoa thi phai kiem tra no co ton tai hay khong
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        //tim san pham theo id
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        //lay danh sach san pham theo categoryId
        return productRepository.findByCategoryId(categoryId);
    }
}
