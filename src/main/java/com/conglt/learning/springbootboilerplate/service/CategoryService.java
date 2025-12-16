package com.conglt.learning.springbootboilerplate.service;

import com.conglt.learning.springbootboilerplate.model.Category;

import java.util.List;

public interface CategoryService {
    //Dinh nghia ham them moi
    Category createCategory(Category category);

    //Dinh nghia ham lay danh sach
    List<Category> getAllCategories();

    //Dinh nghia ham lay chi tiet theo ID
    Category getCategoryById(Long id);

    //Dinh nghia ham cap nhat
    Category updateCategory(Long id, Category category);

    //Dinh nghia ham xoa
    void deleteCategory(Long id);
}
