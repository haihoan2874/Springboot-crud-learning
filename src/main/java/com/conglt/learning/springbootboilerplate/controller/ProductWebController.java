package com.conglt.learning.springbootboilerplate.controller;

import com.conglt.learning.springbootboilerplate.model.Product;
import com.conglt.learning.springbootboilerplate.repository.CategoryRepository;
import com.conglt.learning.springbootboilerplate.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductWebController {

    private final ProductService productService;
    private final CategoryRepository categoryRepository;

    // 1. Xem danh sách
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        return "product-list";
    }

    // 2. Form Thêm mới
    @GetMapping("/new")
    public String showNewProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepository.findAll());
        return "product-form";
    }

    // 3. Form Sửa (Edit) - MỚI
    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        // Lấy sản phẩm cũ từ DB lên để sửa
        Product product = productService.getProductById(id); // Cần đảm bảo Service có hàm này
        model.addAttribute("product", product);

        // QUAN TRỌNG: Phải gửi lại danh sách danh mục để người dùng chọn lại nếu muốn
        model.addAttribute("categories", categoryRepository.findAll());

        return "product-form"; // Tái sử dụng form thêm mới
    }

    // 4. Xóa (Delete) - MỚI
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id); // Cần đảm bảo Service có hàm này
        return "redirect:/products";
    }

    // 5. Lưu (Dùng chung cho cả Thêm mới và Sửa)
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        // Nếu product có ID -> Update. Nếu không có ID -> Create
        productService.createProduct(product);
        return "redirect:/products";
    }
}