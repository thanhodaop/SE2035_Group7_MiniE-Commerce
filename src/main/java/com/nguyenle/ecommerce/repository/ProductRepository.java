package com.nguyenle.ecommerce.repository;

import com.nguyenle.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByNameContainingIgnoreCaseAndPriceBetween(
            String name, BigDecimal minPrice, BigDecimal maxPrice);
}