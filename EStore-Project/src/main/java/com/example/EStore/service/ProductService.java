package com.example.EStore.service;

import com.example.EStore.model.dto.ProductDetailDTO;
import com.example.EStore.model.entity.*;
import com.example.EStore.repository.ProductSizeRepository;
import com.example.EStore.repository.ProductTypeRepository;
import com.example.EStore.model.dto.AddProductDTO;
import com.example.EStore.model.views.ProductView;
import com.example.EStore.model.enums.GenderEntityEnum;
import com.example.EStore.model.enums.ProductSize;
import com.example.EStore.model.enums.ProductTypeEnum;
import com.example.EStore.repository.GenderRepository;
import com.example.EStore.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private ImageCloudService imageCloudService;

    private GenderRepository genderRepository;

    private ProductTypeRepository productTypeRepository;
    private ProductSizeRepository productSizeRepository;

    public ProductService(ProductRepository productRepository, ImageCloudService imageCloudService, GenderRepository genderRepository, ProductTypeRepository productTypeRepository, ProductSizeRepository productentityRepository) {
        this.productRepository = productRepository;
        this.imageCloudService = imageCloudService;
        this.genderRepository = genderRepository;
        this.productTypeRepository = productTypeRepository;
        this.productSizeRepository = productentityRepository;
    }

    public void createProduct(AddProductDTO addProductDTO) {
        List<ImageEntity> allImages = new ArrayList<>();

        for (MultipartFile image : addProductDTO.getImages()) {
            String imageUrl = imageCloudService.saveImage(image);
            ImageEntity imageEntity = new ImageEntity()
                    .setTitle(image.getOriginalFilename())
                    .setUrl(imageUrl);

            allImages.add(imageEntity);
        }


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

        List<ProductSizeEntity> productSizes = new ArrayList<>();

        List<String> allSizesFromDTO = addProductDTO.getSize();

        for (String currentSize : allSizesFromDTO) {
          ProductSize size = switch (currentSize) {
                case "1" -> ProductSize.XS;
                case "2" -> ProductSize.S;
                case "3" -> ProductSize.M;
                case "4" -> ProductSize.L;
                case "5" -> ProductSize.XL;
              default -> throw new UnsupportedOperationException();
            };

            Optional<ProductSizeEntity> byProductSize = this.productSizeRepository.findByProductSize(size);
            productSizes.add(byProductSize.get());
        }

        ProductEntity productEntity = new ProductEntity()
                .setName(addProductDTO.getName())
                .setAvailable(true)
                .setColour(addProductDTO.getColour())
                .setDescription(addProductDTO.getDescription())
                .setPrice(BigDecimal.valueOf(addProductDTO.getPrice()))
                .setSizes(productSizes)
                .setSpecifications(addProductDTO.getSpecifications())
                .setProductType(productType)
                .setGender(gender)
                .setQuantity(addProductDTO.getQuantity())
                .setDateOfUploading(addProductDTO.getUploadedAt())
                .setImages(allImages);

        for (ImageEntity image : allImages) {
            image.setProduct(productEntity);
        }

        this.productRepository.save(productEntity);
    }

    public List<ProductView> getAllProducts() {
        return this.productRepository.findAll().stream().map(this::mapProductEntityToProductView).collect(Collectors.toList());
    }

    public List<ProductSizeEntity> getAllSizes() {
        return this.productSizeRepository.findAll();
    }

    public ProductDetailDTO getProductDetailDTO(Long id) {
        Optional<ProductDetailDTO> productDetailDTO = this.productRepository.findById(id).map(this::mapProductEntityToProductDetailDTO);

        return productDetailDTO.get();
    }

    private ProductDetailDTO mapProductEntityToProductDetailDTO(ProductEntity productEntity) {
       ProductDetailDTO productDetailDTO = new ProductDetailDTO();

       productDetailDTO.setId(productEntity.getId())
               .setColour(productEntity.getColour())
               .setName(productEntity.getName())
               .setSpecifications(productEntity.getSpecifications())
               .setPrice(productEntity.getPrice().doubleValue())
               .setDescription(productEntity.getDescription())
               .setSize(productEntity.getSizes().stream().map(size -> size.getProductSize().name()).collect(Collectors.toList()))
               .setThumbnailUrls(productEntity.getImages().stream().map(ImageEntity::getUrl).collect(Collectors.toList()));

        return productDetailDTO;
    }

    private ProductView mapProductEntityToProductView(ProductEntity productEntity) {
        ProductView productView = new ProductView();
        productView.setId(productEntity.getId());
        productView.setName(productEntity.getName());
        productView.setPrice(productEntity.getPrice().doubleValue())
                .setSpecifications(productEntity.getSpecifications())
                .setThumbnailUrl(productEntity.getImages().get(0).getUrl());

        return productView;
    }

    public ProductEntity getProductById(Long productId) {
        return this.productRepository.findById(productId).get();
    }
}
