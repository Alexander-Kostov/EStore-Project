package com.example.EStore.service;

import com.example.EStore.ProductTypeRepository.ProductTypeRepository;
import com.example.EStore.model.dto.AddProductDTO;
import com.example.EStore.model.entity.GenderEntity;
import com.example.EStore.model.entity.ImageEntity;
import com.example.EStore.model.entity.ProductEntity;
import com.example.EStore.model.entity.ProductTypeEntity;
import com.example.EStore.model.enums.GenderEntityEnum;
import com.example.EStore.model.enums.ProductSize;
import com.example.EStore.model.enums.ProductTypeEnum;
import com.example.EStore.repository.GenderRepository;
import com.example.EStore.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private ImageCloudService imageCloudService;

    private GenderRepository genderRepository;

    private ProductTypeRepository productTypeRepository;

    public ProductService(ProductRepository productRepository, ImageCloudService imageCloudService, GenderRepository genderRepository, ProductTypeRepository productTypeRepository) {
        this.productRepository = productRepository;
        this.imageCloudService = imageCloudService;
        this.genderRepository = genderRepository;
        this.productTypeRepository = productTypeRepository;
    }
    public void createProduct(AddProductDTO addProductDTO) {


        MultipartFile image = addProductDTO.getImage();

        String imageUrl = imageCloudService.saveImage(image);

        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setTitle(image.getOriginalFilename())
                .setUrl(imageUrl);

        GenderEntityEnum genderEnum = switch (addProductDTO.getGender()) {
            case "M" -> GenderEntityEnum.MALE;
            case "F" -> GenderEntityEnum.FEMALE;
            case "K" -> GenderEntityEnum.KID;
            default -> GenderEntityEnum.MALE;
        };

       GenderEntity gender = this.genderRepository.findByGender(genderEnum);

        ProductTypeEnum productEnum = switch (addProductDTO.getType()) {
            case "DRESS" -> ProductTypeEnum.DRESS;
            case "SKIRT" -> ProductTypeEnum.SKIRT;
            case "JEANS" -> ProductTypeEnum.JEANS;
            case "SOCKS" -> ProductTypeEnum.SOCKS;
            case "T_SHIRT" -> ProductTypeEnum.T_SHIRT;
            default -> throw new UnsupportedOperationException("Invalid product type!");
        };

        ProductTypeEntity productType = this.productTypeRepository.findByProductType(productEnum);

        ProductSize productSize = switch (addProductDTO.getSize()) {
            case "XS" -> ProductSize.XS;
            case "S" -> ProductSize.S;
            case "M" -> ProductSize.M;
            case "L" -> ProductSize.L;
            case "XL" -> ProductSize.XL;
            default -> throw new UnsupportedOperationException("Invalid size!");
        };

        ProductEntity productEntity = new ProductEntity()
                .setName(addProductDTO.getName())
                .setAvailable(true)
                .setColour(addProductDTO.getColour())
                .setDescription(addProductDTO.getDescription())
                .setPrice(BigDecimal.valueOf(addProductDTO.getPrice()))
                .setSize(productSize)
                .setProductType(productType)
                .setGender(gender)
                .setQuantity(addProductDTO.getQuantity())
                .setDateOfUploading(addProductDTO.getUploadedAt())
                .setImages(List.of(imageEntity));


             imageEntity.setProduct(productEntity);

        this.productRepository.save(productEntity);
    }
}
