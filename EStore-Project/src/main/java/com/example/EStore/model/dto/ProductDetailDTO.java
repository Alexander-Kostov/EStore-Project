package com.example.EStore.model.dto;

import java.util.List;

public class ProductDetailDTO {

    private long id;

    private String name;

    private List<String> thumbnailUrls;

    private double price;

    private String specifications;

    private String description;

    private int quantity;

    private List<String> size;

    private String colour;

    public long getId() {
        return id;
    }

    public ProductDetailDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDetailDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getThumbnailUrls() {
        return thumbnailUrls;
    }

    public ProductDetailDTO setThumbnailUrls(List<String> thumbnailUrls) {
        this.thumbnailUrls = thumbnailUrls;
        return this;
    }

    public List<String> getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public ProductDetailDTO setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getSpecifications() {
        return specifications;
    }

    public ProductDetailDTO setSpecifications(String specifications) {
        this.specifications = specifications;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDetailDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<String> getSizes() {
        return size;
    }

    public ProductDetailDTO setSize(List<String> size) {
        this.size = size;
        return this;
    }

    public String getColour() {
        return colour;
    }

    public ProductDetailDTO setColour(String colour) {
        this.colour = colour;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductDetailDTO setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

}
