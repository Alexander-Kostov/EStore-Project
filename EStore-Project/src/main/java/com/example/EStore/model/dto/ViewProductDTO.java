package com.example.EStore.model.dto;

public class ViewProductDTO {

    private long id;

    private String name;

    private String thumbnailUrl;

    private double price;

    private String specifications;

    public long getId() {
        return id;
    }

    public ViewProductDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ViewProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public ViewProductDTO setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ViewProductDTO setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getSpecifications() {
        return specifications;
    }

    public ViewProductDTO setSpecifications(String specifications) {
        this.specifications = specifications;
        return this;
    }
}
