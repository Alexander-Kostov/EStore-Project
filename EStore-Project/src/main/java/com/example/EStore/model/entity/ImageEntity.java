package com.example.EStore.model.entity;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "images")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    private ProductEntity product;

    public long getId() {
        return id;
    }

    public ImageEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ImageEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ImageEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public ImageEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }
}
