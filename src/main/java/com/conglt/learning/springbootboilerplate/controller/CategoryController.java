package com.conglt.learning.springbootboilerplate.controller;

import com.conglt.learning.springbootboilerplate.model.Category;
import com.conglt.learning.springbootboilerplate.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories") //duong dan api
@RequiredArgsConstructor //tiem service vao day
public class CategoryController {
    private final CategoryService categoryService;

    //API tao moi (post)
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        //@RequestBody de lay du lieu tu body gui len
        Category newCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(newCategory);//tra ve ket qua
    }

    //API lay danh sach (GET+id)
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    //API lay chi tiet theo ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        //@PathVariable de lay id tu duong dan
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    //API cap nhat (PUT+id)
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }

    //API xoa (DELETE + id)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
