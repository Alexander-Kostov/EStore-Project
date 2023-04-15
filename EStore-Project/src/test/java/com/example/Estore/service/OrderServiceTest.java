package com.example.Estore.service;

import com.example.EStore.model.entity.*;
import com.example.EStore.model.enums.OrderActionEnum;
import com.example.EStore.repository.CartItemRepository;
import com.example.EStore.repository.OrderRepository;
import com.example.EStore.repository.OrderedProductRepository;
import com.example.EStore.repository.ProductRepository;
import com.example.EStore.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderedProductRepository orderedProductRepository;

    @Mock
    private CartItemRepository cartRepo;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testAddOrderForCurrentCustomer() {
        // create a mock user entity
        UserEntity customer = new UserEntity();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");

        // create a mock ordered product entity
        OrderedProductEntity orderedProduct = new OrderedProductEntity();
        orderedProduct.setId(1L);
        orderedProduct.setQuantity(2);
        orderedProduct.setPrice(BigDecimal.valueOf(10));

        ProductEntity product = new ProductEntity();
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(BigDecimal.valueOf(5));
        product.setQuantity(10);
        orderedProduct.setProduct(product);
        List<OrderedProductEntity> allCartItemsForCurrentUser = Collections.singletonList(orderedProduct);

        // mock the behavior of the repositories
        when(orderedProductRepository.findByCustomerId(customer.getId())).thenReturn(allCartItemsForCurrentUser);
        when(productRepository.findById(orderedProduct.getProduct().getId())).thenReturn(Optional.of(product));

        // call the method under test
        orderService.addOrderForCurrentCustomer(customer);

        // verify that the repositories were called with the expected arguments
        verify(productRepository, times(1)).save(product);
        verify(orderedProductRepository, times(1)).deleteAll();
        verify(cartRepo, times(1)).deleteAll();
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
    }

    @Test
    public void testChangeAllActions() {
        // Create some sample orders
        OrderEntity order1 = new OrderEntity().setAction(OrderActionEnum.InStorage);
        OrderEntity order2 = new OrderEntity().setAction(OrderActionEnum.SHIPPED);
        OrderEntity order3 = new OrderEntity().setAction(OrderActionEnum.DELIVERED);
        List<OrderEntity> orders = Arrays.asList(order1, order2, order3);

        // Setup mock behavior
        when(orderRepository.findAll()).thenReturn(orders);

        // Call the method under test
        orderService.changeAllActions();

        // Verify that the actions were updated correctly
        verify(orderRepository, times(1)).saveAll(orders);
        assertEquals(OrderActionEnum.SHIPPED, order1.getAction());
        assertEquals(OrderActionEnum.DELIVERED, order2.getAction());
        assertEquals(OrderActionEnum.InStorage, order3.getAction());
    }
}


