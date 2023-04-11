package com.example.EStore.service;

import com.example.EStore.model.entity.CustomerProduct;
import com.example.EStore.model.entity.*;
import com.example.EStore.model.enums.OrderActionEnum;
import com.example.EStore.model.enums.OrderStatusEnum;
import com.example.EStore.repository.CartItemRepository;
import com.example.EStore.repository.OrderRepository;
import com.example.EStore.repository.OrderedProductRepository;
import com.example.EStore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderedProductRepository orderedProductRepository;

    private CartItemRepository cartRepo;

    private ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, OrderedProductRepository orderedProductRepository, CartItemRepository cartRepo, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderedProductRepository = orderedProductRepository;
        this.cartRepo = cartRepo;
        this.productRepository = productRepository;
    }

    public void addOrderForCurrentCustomer(UserEntity customer, List<CartItemEntity> allCartItemsForCurrentUser) {

        List<OrderedProductEntity> allOrderedItems = this.orderedProductRepository.findByCustomerId(customer.getId());


        double totalPrice= 5;

        for (OrderedProductEntity orderedItem : allOrderedItems) {
            Optional<ProductEntity> product = this.productRepository.findById(orderedItem.getProduct().getId());
            product.get().setQuantity(product.get().getQuantity() - orderedItem.getQuantity());

            this.productRepository.save(product.get());
            totalPrice += orderedItem.getPrice().doubleValue() * orderedItem.getQuantity();
        }

        OrderEntity order = new OrderEntity();
        order.setCustomer(customer)
                .setAction(OrderActionEnum.InStorage)
                .setStatus(OrderStatusEnum.APPROVED)
                .setItems(allOrderedItems.stream().map(this::getProductsMatchingOrderedItems).collect(Collectors.toList()))
                .setLocalDate(LocalDate.now())
                .setTotalPrice(totalPrice);

        this.orderRepository.save(order);

        this.cartRepo.deleteAll();
        this.orderedProductRepository.deleteAll();
    }

    private ProductEntity getProductsMatchingOrderedItems(OrderedProductEntity orderedProduct) {
        return this.productRepository.findById(orderedProduct.getProduct().getId()).get();
    }


    public void changeAllActions() {
        List<OrderEntity> allOrders = this.orderRepository.findAll();

        for (OrderEntity order : allOrders) {
           if(order.getAction() == OrderActionEnum.InStorage){
               order.setAction(OrderActionEnum.SHIPPED);
           }else if(order.getAction() == OrderActionEnum.SHIPPED) {
               order.setAction(OrderActionEnum.DELIVERED);
           }else {
               order.setAction(OrderActionEnum.InStorage);
           }
        }

        this.orderRepository.saveAll(allOrders);
    }
}
