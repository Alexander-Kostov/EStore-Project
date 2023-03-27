package com.example.EStore.model.entity;

import com.example.EStore.model.enums.ProductTypeEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "product_type")
public class ProductTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductTypeEnum productType;

    public ProductTypeEntity(ProductTypeEnum productType) {
        this.productType = productType;
    }

    public ProductTypeEntity() {
    }

    public long getId() {
        return id;
    }

    public ProductTypeEntity setId(long id) {
        this.id = id;
        return this;
    }

    public ProductTypeEnum getProductType() {
        return productType;
    }

    public ProductTypeEntity setProductType(ProductTypeEnum productTypeEnum) {
        this.productType = productTypeEnum;
        return this;
    }
}
