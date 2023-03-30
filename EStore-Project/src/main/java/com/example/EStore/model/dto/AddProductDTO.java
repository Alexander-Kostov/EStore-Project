package com.example.EStore.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public class AddProductDTO {
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 symbols!")
    private String name;
    @Size(min = 2, max = 200, message = "Description must be between 2 and 200 symbols!")
    private String description;
    @NotNull(message = "You must put a colour!")
    private String colour;
    @Positive(message = "Price must be a positive number!")
    @NotNull(message = "You must put a price!")
    private double price;
    @NotNull(message = "You must select a size!")
    private List<String> size;
    @Positive(message = "Quantity must be a positive number!")
    private int quantity;
    @NotNull(message = "You must select a gender!")
    private String gender;
    @NotNull(message = "You must select a type!")
    private String type;
    @PastOrPresent(message = "Uploaded date cannot be in the future!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate uploadedAt;
    @NotNull(message = "You must upload an image!")
    private List<MultipartFile> images;

    @Size(min = 5, max = 100)
    private String specifications;

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

    public List<String> getSize() {
        return size;
    }

    public AddProductDTO setSize(List<String> size) {
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

    public String getSpecifications() {
        return specifications;
    }

    public AddProductDTO setSpecifications(String specifications) {
        this.specifications = specifications;
        return this;
    }

    public LocalDate getUploadedAt() {
        return uploadedAt;
    }

    public AddProductDTO setUploadedAt(LocalDate uploadedAt) {
        this.uploadedAt = uploadedAt;
        return this;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public AddProductDTO setImages(List<MultipartFile> images) {
        this.images = images;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public AddProductDTO setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
}
