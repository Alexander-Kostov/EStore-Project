package com.example.EStore.model.dto;


public class CartProductDTO {

    private long productId;

    private double productPrice;

    private String productSize;

    private int productQuantity;

    private String productColour;

    public long getProductId() {
        return productId;
    }

    public CartProductDTO setProductId(long productId) {
        this.productId = productId;
        return this;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public CartProductDTO setProductPrice(double productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    public String getProductSize() {
        return productSize;
    }

    public CartProductDTO setProductSize(String productSize) {
        this.productSize = productSize;
        return this;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public CartProductDTO setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
        return this;
    }

    public String getProductColour() {
        return productColour;
    }

    public CartProductDTO setProductColour(String productColour) {
        this.productColour = productColour;
        return this;
    }

    public long settingIdFromDTO(Long id) {
        return this.productId = id;
    }
}
