package com.conglt.learning.springbootboilerplate.service.impl;

import com.conglt.learning.springbootboilerplate.model.Category;
import com.conglt.learning.springbootboilerplate.repository.CategoryRepository;
import com.conglt.learning.springbootboilerplate.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //Danh dau cho Spring biet day la Service
@RequiredArgsConstructor //Tu dong tiem inject cai Repository vao de dung
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository; //goi ong thu kho vao day


    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        //Buoc 1: tim xem thang cu co ton tai hya khong?
        Category existingCategory = getCategoryById(id);

        //Buoc 2: Cap nhat thong tin moi vao thang cu
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
        existingCategory.setIcon(category.getIcon());

        //Buoc 3: luu lai
        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        //truoc khi xoa thi phai kiem tra no co ton tai hay khong
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
