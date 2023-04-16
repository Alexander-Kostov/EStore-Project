package com.example.Estore.service;

import com.example.EStore.model.dto.ProductDetailDTO;
import com.example.EStore.model.entity.*;
import com.example.EStore.model.enums.GenderEntityEnum;
import com.example.EStore.repository.CartItemRepository;
import com.example.EStore.repository.OrderedProductRepository;
import com.example.EStore.service.ProductService;
import com.example.EStore.service.ShoppingCartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartServiceTest {

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private OrderedProductRepository orderedProductRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    @Test
    void addToCart_ShouldSaveOrderedProductAndCartItem() {
        // Arrange
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setQuantity(2);
        productDetailDTO.setSize(Arrays.asList("M"));

        ProductEntity product = new ProductEntity();
        product.setId(1L);
        product.setPrice(BigDecimal.valueOf(10));
        product.setColour("Red");
        product.setGender(new GenderEntity(GenderEntityEnum.MALE));
        product.setImages(Collections.singletonList(new ImageEntity()));
        product.setName("Test Product");

        UserEntity customer = new UserEntity();
        customer.setId(1L);

        ArgumentCaptor<OrderedProductEntity> orderedProductCaptor = ArgumentCaptor.forClass(OrderedProductEntity.class);
        ArgumentCaptor<CartItemEntity> cartItemCaptor = ArgumentCaptor.forClass(CartItemEntity.class);

        // Act
        shoppingCartService.addToCart(productDetailDTO, product, customer);

        // Assert
        verify(orderedProductRepository).save(orderedProductCaptor.capture());
        OrderedProductEntity savedOrderedProduct = orderedProductCaptor.getValue();
        assertThat(savedOrderedProduct.getProduct()).isEqualTo(product);
        assertThat(savedOrderedProduct.getCustomer()).isEqualTo(customer);
        assertThat(savedOrderedProduct.getQuantity()).isEqualTo(productDetailDTO.getQuantity());
        assertThat(savedOrderedProduct.getColour()).isEqualTo(product.getColour());
        assertThat(savedOrderedProduct.getPictureUrl()).isEqualTo(product.getImages().get(0).getUrl());
        assertThat(savedOrderedProduct.getSex()).isEqualTo(product.getGender().getGender().name());
        assertThat(savedOrderedProduct.getSize()).isEqualTo(productDetailDTO.getSize().get(0));
        assertThat(savedOrderedProduct.getPrice()).isEqualTo(product.getPrice());

        verify(cartItemRepository).save(cartItemCaptor.capture());
        CartItemEntity savedCartItem = cartItemCaptor.getValue();
        assertThat(savedCartItem.getCustomer()).isEqualTo(customer);
        assertThat(savedCartItem.getQuantity()).isEqualTo(productDetailDTO.getQuantity());
        assertThat(savedCartItem.getOrderedProduct()).isEqualTo(savedOrderedProduct);
    }

    @Test
    void deleteOrderedProductById_ShouldDeleteCartItemAndOrderedProduct() {
        // Arrange
        Long id = 1L;

        // Act
        shoppingCartService.deleteOrderedProductById(id);

        // Assert
        verify(cartItemRepository).deleteById(id);
        verify(orderedProductRepository).deleteById(id);
    }


    @Test
    public void getAllCartItemsForCurrentUser_ShouldReturnListOfCartItems_WhenGivenLoggedUser() {
        // Arrange
        UserEntity loggedUser = new UserEntity();
        loggedUser.setId(1L);
        loggedUser.setEmail("testuser@example.com");

        List<CartItemEntity> cartItems = new ArrayList<>();
        cartItems.add(new CartItemEntity());
        cartItems.add(new CartItemEntity());
        cartItems.add(new CartItemEntity());

        when(cartItemRepository.findByCustomer(loggedUser)).thenReturn(cartItems);

        // Act
        List<CartItemEntity> result = shoppingCartService.getAllCartItemsForCurrentUser(loggedUser);

        // Assert
        assertEquals(cartItems.size(), result.size());
        assertEquals(cartItems, result);
        Mockito.verify(cartItemRepository, Mockito.times(1)).findByCustomer(loggedUser);
    }

    @Test
    void sumAllProductsInCart_ShouldReturnCorrectSum() {
        // Arrange
        CartItemEntity cartItem1 = new CartItemEntity();
        cartItem1.setQuantity(2);
        OrderedProductEntity orderedProduct1 = new OrderedProductEntity();
        orderedProduct1.setPrice(BigDecimal.valueOf(10));
        cartItem1.setOrderedProduct(orderedProduct1);

        CartItemEntity cartItem2 = new CartItemEntity();
        cartItem2.setQuantity(1);
        OrderedProductEntity orderedProduct2 = new OrderedProductEntity();
        orderedProduct2.setPrice(BigDecimal.valueOf(5));
        cartItem2.setOrderedProduct(orderedProduct2);

        List<CartItemEntity> cartItems = Arrays.asList(cartItem1, cartItem2);

        ShoppingCartService shoppingCartService = new ShoppingCartService(null, null, null);

        // Act
        double result = shoppingCartService.sumAllProductsInCart(cartItems);

        // Assert
        assertEquals(25.0, result);
    }

    @Test
    void checkForEnoughQuantity_WithEnoughQuantity_ReturnsNull() {
        // Arrange
        UserEntity customer = new UserEntity();
        customer.setId(1L);
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        product.setName("Test Product");
        product.setQuantity(5);
        OrderedProductEntity orderedProduct = new OrderedProductEntity();
        orderedProduct.setCustomer(customer);
        orderedProduct.setProduct(product);
        orderedProduct.setQuantity(2);
        List<OrderedProductEntity> orderedProducts = new ArrayList<>();
        orderedProducts.add(orderedProduct);
        when(orderedProductRepository.findByCustomerId(customer.getId())).thenReturn(orderedProducts);

        // Act
        OrderedProductEntity result = shoppingCartService.checkForEnoughQuantity(customer);

        // Assert
        assertNull(result);
    }


    @Test
    void checkForEnoughQuantity_WithNotEnoughQuantity_ReturnsOrderedProduct() {
        // Arrange
        UserEntity customer = new UserEntity();
        customer.setId(1L);
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        product.setName("Test Product");
        product.setQuantity(5);
        OrderedProductEntity orderedProduct = new OrderedProductEntity();
        orderedProduct.setCustomer(customer);
        orderedProduct.setProduct(product);
        orderedProduct.setQuantity(7);
        List<OrderedProductEntity> orderedProducts = new ArrayList<>();
        orderedProducts.add(orderedProduct);
        when(orderedProductRepository.findByCustomerId(customer.getId())).thenReturn(orderedProducts);

        // Act
        OrderedProductEntity result = shoppingCartService.checkForEnoughQuantity(customer);

        // Assert
        assertNotNull(result);
        assertEquals(orderedProduct, result);
    }
}

