package com.example.EStore.model.dto;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddProductDTO {

    private String name;

    private String description;

    private String colour;

    private String size;

    private double price;

    private String gender;

    private String type;

    private LocalDate uploadedAt;

    private MultipartFile image;

    public String getName() {
        return name;
    }

    public AddProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddProductDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getColour() {
        return colour;
    }

    public AddProductDTO setColour(String colour) {
        this.colour = colour;
        return this;
    }

    public String getSize() {
        return size;
    }

    public AddProductDTO setSize(String size) {
        this.size = size;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public AddProductDTO setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public AddProductDTO setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getType() {
        return type;
    }

    public AddProductDTO setType(String type) {
        this.type = type;
        return this;
    }

    public LocalDate getUploadedAt() {
        return uploadedAt;
    }

    public AddProductDTO setUploadedAt(LocalDate uploadedAt) {
        this.uploadedAt = uploadedAt;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public AddProductDTO setImage(MultipartFile image) {
        this.image = image;
        return this;
    }


}
