package com.example.EStore.service;

import com.example.EStore.model.entity.ImageEntity;
import com.example.EStore.model.entity.ProductEntity;
import com.example.EStore.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private ImageCloudService imageCloudService;

    public ProductService(ProductRepository productRepository, ImageCloudService imageCloudService) {
        this.productRepository = productRepository;
        this.imageCloudService = imageCloudService;
    }
    public ProductEntity createProduct(ProductEntity productEntity, MultipartFile image) {
        String imageUrl = imageCloudService.saveImage(image);

        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setTitle(image.getOriginalFilename())
                .setUrl(imageUrl);

        productEntity.setImage(List.of(imageEntity));

        this.productRepository.save(productEntity);

        return productEntity;
    }
}
