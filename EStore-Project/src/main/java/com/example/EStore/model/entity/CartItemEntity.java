package com.example.EStore.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private OrderedProductEntity orderedProduct;

    @ManyToOne
    private UserEntity customer;

    private int quantity;


    public Long getId() {
        return id;
    }

    public CartItemEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public OrderedProductEntity getOrderedProduct() {
        return orderedProduct;
    }

    public CartItemEntity setOrderedProduct(OrderedProductEntity orderedProduct) {
        this.orderedProduct = orderedProduct;
        return this;
    }

    public UserEntity getCustomer() {
        return customer;
    }

    public CartItemEntity setCustomer(UserEntity customer) {
        this.customer = customer;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public CartItemEntity setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
}
