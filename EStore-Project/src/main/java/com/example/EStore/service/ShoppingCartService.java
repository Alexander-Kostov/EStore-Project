package com.example.EStore.service;

import com.example.EStore.model.dto.ProductDetailDTO;
import com.example.EStore.model.entity.CartItemEntity;
import com.example.EStore.model.entity.OrderedProductEntity;
import com.example.EStore.model.entity.ProductEntity;
import com.example.EStore.model.entity.UserEntity;
import com.example.EStore.repository.CartItemRepository;
import com.example.EStore.repository.OrderedProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    private CartItemRepository cartItemRepository;

    private OrderedProductRepository orderedProductRepository;

    private ProductService productService;


    public ShoppingCartService(CartItemRepository cartRepository, OrderedProductRepository orderRepository, ProductService productService) {
        this.cartItemRepository = cartRepository;
        this.orderedProductRepository = orderRepository;
        this.productService = productService;
    }


    public void addToCart(ProductDetailDTO productDetailDTO, ProductEntity product, UserEntity customer) {

        OrderedProductEntity orderedProductEntity = new OrderedProductEntity()
                .setProduct(product)
                .setCustomer(customer)
                .setQuantity(productDetailDTO.getQuantity())
                .setColour(product.getColour())
                .setPictureUrl(product.getImages().get(0).getUrl())
                .setSex(product.getGender().getGender().name())
                .setSize(productDetailDTO.getSize().get(0))
                .setPrice(product.getPrice());

        this.orderedProductRepository.save(orderedProductEntity);

        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setCustomer(customer)
                .setQuantity(productDetailDTO.getQuantity())
                .setOrderedProduct(orderedProductEntity);

        cartItemRepository.save(cartItemEntity);
    }

    public void deleteOrderedProductById(Long id) {
        this.cartItemRepository.deleteById(id);
        this.orderedProductRepository.deleteById(id);
    }

    public List<CartItemEntity> getAllCartItemsForCurrentUser(UserEntity loggedUser) {
        return this.cartItemRepository.findByCustomer(loggedUser);
    }

    public double sumAllProductsInCart(List<CartItemEntity> cartItems) {
        double totalSum = 0;

        for (CartItemEntity cartItem : cartItems) {
            totalSum += cartItem.getOrderedProduct().getPrice().doubleValue() * cartItem.getQuantity();
        }

        return totalSum;
    }

    public OrderedProductEntity checkForEnoughQuantity(UserEntity customer) {
        List<OrderedProductEntity> allOrderedProducts = this.orderedProductRepository.findByCustomerId(customer.getId());

        for (OrderedProductEntity orderedProduct : allOrderedProducts) {
            if (orderedProduct.getQuantity() > orderedProduct.getProduct().getQuantity()) {
                return orderedProduct;
            }
        }

        return null;
    }
}
