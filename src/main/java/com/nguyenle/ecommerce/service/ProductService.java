package com.nguyenle.ecommerce.service;

import com.nguyenle.ecommerce.entities.Product;
import com.nguyenle.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public List<Product> searchProducts(String keyword, BigDecimal minPrice, BigDecimal maxPrice) {
        if (keyword == null) {
            keyword = "";
        }
        if (minPrice == null) {
            minPrice = BigDecimal.ZERO;
        }
        if (maxPrice == null) {
            maxPrice = new BigDecimal("999999999999");
        }
        return productRepository.findByNameContainingIgnoreCaseAndPriceBetween(keyword, minPrice, maxPrice);
    }
}