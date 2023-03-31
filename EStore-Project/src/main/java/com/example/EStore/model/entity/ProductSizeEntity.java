package com.example.EStore.model.entity;

import com.example.EStore.model.enums.ProductSize;
import jakarta.persistence.*;

@Entity
@Table(name = "product_sizes")
public class ProductSizeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductSize productSize;


    public ProductSizeEntity (ProductSize productSize) {
        this.productSize = productSize;
    }

    public ProductSizeEntity() {
    }

    public Long getId() {
        return id;
    }

    public ProductSizeEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public ProductSize getProductSize() {
        return productSize;
    }

    public ProductSizeEntity setProductSize(ProductSize productSize) {
        this.productSize = productSize;
        return this;
    }
}
