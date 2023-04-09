package com.example.EStore.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ordered_products")
public class OrderedProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private UserEntity customer;

    @ManyToOne
    private ProductEntity product;

    private BigDecimal price;

    private int quantity;

    private String size;

    private String colour;

    private String sex;

    private String pictureUrl;


    public long getId() {
        return id;
    }

    public OrderedProductEntity setId(long id) {
        this.id = id;
        return this;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public OrderedProductEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

    public UserEntity getCustomer() {
        return customer;
    }

    public OrderedProductEntity setCustomer(UserEntity customer) {
        this.customer = customer;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderedProductEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderedProductEntity setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getSize() {
        return size;
    }

    public OrderedProductEntity setSize(String size) {
        this.size = size;
        return this;
    }

    public String getColour() {
        return colour;
    }

    public OrderedProductEntity setColour(String colour) {
        this.colour = colour;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public OrderedProductEntity setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public OrderedProductEntity setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }
}
