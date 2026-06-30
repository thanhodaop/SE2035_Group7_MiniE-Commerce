package com.nguyenle.ecommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "NVARCHAR(255)", nullable = false)
    private String name;

    private BigDecimal price;

    @Column(columnDefinition = "NVARCHAR(1000)")
    private String description;

    @Column(columnDefinition = "NVARCHAR(500)")
    private String imageUrl;

    private Integer quantity;

    private Integer discountPercent;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Transient
    public boolean isOnSale() {
        return discountPercent != null && discountPercent > 0;
    }

    @Transient
    public BigDecimal getDiscountedPrice() {
        if (!isOnSale() || price == null) {
            return price;
        }
        BigDecimal percent = BigDecimal.valueOf(discountPercent);
        BigDecimal multiplier = BigDecimal.ONE.subtract(percent.divide(BigDecimal.valueOf(100)));
        return price.multiply(multiplier);
    }
}