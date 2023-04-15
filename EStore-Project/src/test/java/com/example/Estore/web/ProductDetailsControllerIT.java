package com.example.Estore.web;

import com.example.EStore.model.dto.ProductDetailDTO;
import com.example.EStore.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductDetailsControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetProductDetails() {
        // Arrange
        Long productId = 1L;
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setName("Product Name");
        productDetailDTO.setDescription("Product Description");
        productDetailDTO.setPrice(99.99);
        productDetailDTO.setSize(Arrays.asList("S", "M", "L"));
        productDetailDTO.setThumbnailUrls(Arrays.asList("url1", "url2", "url3"));
        Mockito.when(productService.getProductDetailDTO(productId)).thenReturn(productDetailDTO);

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity("/products-details/" + productId, String.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("Product Name"));
        assertTrue(response.getBody().contains("Product Description"));
        assertTrue(response.getBody().contains("S"));
        assertTrue(response.getBody().contains("M"));
        assertTrue(response.getBody().contains("L"));
        assertTrue(response.getBody().contains("url1"));
        assertTrue(response.getBody().contains("url2"));
        assertTrue(response.getBody().contains("url3"));
    }
}