package com.example.Estore.service;

import com.example.EStore.model.dto.AddProductDTO;
import com.example.EStore.model.entity.*;
import com.example.EStore.model.enums.GenderEntityEnum;
import com.example.EStore.model.enums.ProductSize;
import com.example.EStore.model.enums.ProductTypeEnum;
import com.example.EStore.model.views.ProductView;
import com.example.EStore.repository.GenderRepository;
import com.example.EStore.repository.ProductRepository;
import com.example.EStore.repository.ProductSizeRepository;
import com.example.EStore.repository.ProductTypeRepository;
import com.example.EStore.service.ImageCloudService;
import com.example.EStore.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ImageCloudService imageCloudService;

    @Mock
    private GenderRepository genderRepository;

    @Mock
    private ProductTypeRepository productTypeRepository;

    @Mock
    private ProductSizeRepository productSizeRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testCreateProduct() throws IOException {
        // create a mock GenderEntity object
        GenderEntity genderEntity = new GenderEntity().setGender(GenderEntityEnum.MALE);

        when(genderRepository.findByGender(GenderEntityEnum.MALE)).thenReturn(genderEntity);

        // create a mock ProductTypeEntity object
        ProductTypeEntity productTypeEntity = new ProductTypeEntity().setProductType(ProductTypeEnum.JEANS);

        when(productTypeRepository.findByProductType(ProductTypeEnum.JEANS)).thenReturn(productTypeEntity);

        // create a mock ProductSizeEntity object
        ProductSizeEntity productSizeEntity = new ProductSizeEntity().setProductSize(ProductSize.XS);

        List<ProductSizeEntity> productSizeEntityList = new ArrayList<>();
        productSizeEntityList.add(productSizeEntity);

        when(productSizeRepository.findByProductSize(ProductSize.XS)).thenReturn(Optional.of(productSizeEntity));

        // create a mock ImageEntity object
        ImageEntity imageEntity = new ImageEntity().setTitle("Image 1").setUrl("url1");

        List<ImageEntity> imageEntityList = new ArrayList<>();
        imageEntityList.add(imageEntity);

        when(imageCloudService.saveImage(any())).thenReturn("url1");

        // create a mock AddProductDTO object
        AddProductDTO addProductDTO = new AddProductDTO()
                .setName("Test Product")
                .setColour("Red")
                .setDescription("Test Description")
                .setPrice(10)
                .setSpecifications("Test specifications")
                .setType("JEANS")
                .setGender("M")
                .setSize(Arrays.asList("1"))
                .setQuantity(5)
                .setUploadedAt(LocalDate.now())
                .setImages(Arrays.asList(mock(MultipartFile.class)));

        // create a ProductService object
        ProductService productService = new ProductService(productRepository, imageCloudService, genderRepository, productTypeRepository, productSizeRepository);

        // call the createProduct method
        productService.createProduct(addProductDTO);

        // verify that the productRepository.save method was called once
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testCreateProduct_withInvalidType_shouldThrowUnsupportedOperationException() {
        // Arrange
        AddProductDTO addProductDTO = new AddProductDTO();
        addProductDTO.setGender("M");
        addProductDTO.setType("INVALID_TYPE");
        addProductDTO.setName("Test Product");
        addProductDTO.setColour("Red");
        addProductDTO.setDescription("Test description");
        addProductDTO.setPrice(9.99);
        addProductDTO.setQuantity(10);
        addProductDTO.setUploadedAt(LocalDate.now());
        addProductDTO.setSpecifications("Test specification");
        addProductDTO.setSize(Collections.singletonList("1"));
        MultipartFile file = new MockMultipartFile("file", "test.png", "image/png", new byte[]{1, 2, 3});
        addProductDTO.setImages(Collections.singletonList(file));

        // Act and Assert
        assertThrows(UnsupportedOperationException.class, () -> productService.createProduct(addProductDTO));
    }


    @Test
    void testCreateProduct_withInvalidSize_shouldThrowUnsupportedOperationException() {
        // Arrange
        AddProductDTO addProductDTO = new AddProductDTO();
        addProductDTO.setGender("M");
        addProductDTO.setType("T_SHIRT");
        addProductDTO.setName("Test Product");
        addProductDTO.setColour("Red");
        addProductDTO.setDescription("Test description");
        addProductDTO.setPrice(9.99);
        addProductDTO.setQuantity(10);
        addProductDTO.setUploadedAt(LocalDate.now());
        addProductDTO.setSpecifications("Test specification");
        addProductDTO.setSize(Collections.singletonList("INVALID_SIZE"));
        MultipartFile file = new MockMultipartFile("file", "test.png", "image/png", new byte[]{1, 2, 3});
        addProductDTO.setImages(Collections.singletonList(file));

        // Act and Assert
        assertThrows(UnsupportedOperationException.class, () -> productService.createProduct(addProductDTO));
    }


    @Test
    public void testGetProductById() {
        Long productId = 1L;
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productId);
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));
        ProductEntity result = productService.getProductById(productId);
        Assertions.assertEquals(productEntity, result);
    }
}




