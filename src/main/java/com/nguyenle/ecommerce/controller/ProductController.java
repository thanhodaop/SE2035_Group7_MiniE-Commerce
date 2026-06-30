package com.nguyenle.ecommerce.controller;

import com.nguyenle.ecommerce.entities.Product;
import com.nguyenle.ecommerce.service.CategoryService;
import com.nguyenle.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/products/search")
    public String searchProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            Model model) {
        model.addAttribute("products", productService.searchProducts(keyword, minPrice, maxPrice));
        model.addAttribute("keyword", keyword);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        return "products";
    }

    @GetMapping("/products/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product-form";
    }

    @PostMapping("/products")
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product-form";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/products/category/{categoryId}")
    public String productsByCategory(@PathVariable Long categoryId, Model model) {
        model.addAttribute("products", productService.getProductsByCategory(categoryId));
        model.addAttribute("category", categoryService.getCategoryById(categoryId));
        return "products-by-category";
    }

    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product-detail";
    }
}