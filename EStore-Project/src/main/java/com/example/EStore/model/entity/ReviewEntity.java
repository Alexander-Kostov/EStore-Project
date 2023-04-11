package com.example.EStore.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000, nullable = false)
    private String text;

    private String username;

    private String email;

    private LocalDate created;
    @ManyToOne
    private UserEntity author;

    @ManyToOne
    private ProductEntity product;


    public Long getId() {
        return id;
    }

    public ReviewEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public ReviewEntity setText(String text) {
        this.text = text;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ReviewEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ReviewEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public ReviewEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public ReviewEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public ReviewEntity setCreated(LocalDate created) {
        this.created = created;
        return this;
    }
}
