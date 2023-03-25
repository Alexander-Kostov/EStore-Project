package com.example.EStore.model.entity;

import com.example.EStore.model.enums.ProductSize;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String colour;

    private ProductSize size;

    private String imageUrl;

    private String description;

    private BigDecimal price;

    private LocalDateTime expiryDate;

    @ManyToOne
    private GenderEntity gender;

    @ManyToOne
    private ProductTypeEntity productType;




    public Long getId() {
        return id;
    }

    public ProductEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getColour() {
        return colour;
    }

    public ProductEntity setColour(String colour) {
        this.colour = colour;
        return this;
    }

    public ProductSize getSize() {
        return size;
    }

    public ProductEntity setSize(ProductSize size) {
        this.size = size;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ProductEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public ProductEntity setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public GenderEntity getGender() {
        return gender;
    }

    public ProductEntity setGender(GenderEntity gender) {
        this.gender = gender;
        return this;
    }

    public ProductTypeEntity getProductType() {
        return productType;
    }

    public ProductEntity setProductType(ProductTypeEntity productType) {
        this.productType = productType;
        return this;
    }
}
