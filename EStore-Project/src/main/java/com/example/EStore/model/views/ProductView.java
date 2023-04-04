package com.example.EStore.model.views;

public class ProductView {

    private long id;

    private String name;

    private String thumbnailUrl;

    private double price;

    private String specifications;

    public long getId() {
        return id;
    }

    public ProductView setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductView setName(String name) {
        this.name = name;
        return this;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public ProductView setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ProductView setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getSpecifications() {
        return specifications;
    }

    public ProductView setSpecifications(String specifications) {
        this.specifications = specifications;
        return this;
    }
}
