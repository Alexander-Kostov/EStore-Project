package com.example.EStore.model.entity;

import com.example.EStore.model.enums.ProductSize;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String colour;

    private String specifications;
    private String description;

    private BigDecimal price;

    private int quantity;
    @Column(name = "date_of_uploading")
    private LocalDate dateOfUploading;

    private boolean available;

    @ManyToOne
    private GenderEntity gender;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<ProductSizeEntity> sizes;

    @ManyToOne
    private ProductTypeEntity productType;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ImageEntity> images;

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

    public String getSpecifications() {
        return specifications;
    }

    public ProductEntity setSpecifications(String specifications) {
        this.specifications = specifications;
        return this;
    }

    public String getColour() {
        return colour;
    }

    public ProductEntity setColour(String colour) {
        this.colour = colour;
        return this;
    }

    public List<ProductSizeEntity> getSizes() {
        return sizes;
    }

    public ProductEntity setSizes(List<ProductSizeEntity> sizes) {
        this.sizes = sizes;
        return this;
    }

    public List<ImageEntity> getImages() {
        return images;
    }

    public ProductEntity setImages(List<ImageEntity> image) {
        this.images = image;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductEntity setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public boolean isAvailable() {
        return available;
    }

    public ProductEntity setAvailable(boolean available) {
        this.available = available;
        return this;
    }

    public LocalDate getDateOfUploading() {
        return dateOfUploading;
    }

    public ProductEntity setDateOfUploading(LocalDate expiryDate) {
        this.dateOfUploading = expiryDate;
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
