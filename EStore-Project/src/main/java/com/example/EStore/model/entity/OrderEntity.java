package com.example.EStore.model.entity;

import com.example.EStore.model.enums.OrderActionEnum;
import com.example.EStore.model.enums.OrderStatusEnum;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<ProductEntity> items;

    @ManyToOne
    private UserEntity customer;

    @Column(name = "local_date")
    private LocalDate localDate;

    @Column(name = "total_price")
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @Enumerated(EnumType.STRING)
    private OrderActionEnum action;

    public Long getId() {
        return id;
    }

    public OrderEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public List<ProductEntity> getItems() {
        return items;
    }

    public OrderEntity setItems(List<ProductEntity> products) {
        this.items = products;
        return this;
    }

    public UserEntity getCustomer() {
        return customer;
    }

    public OrderEntity setCustomer(UserEntity customer) {
        this.customer = customer;
        return this;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public OrderEntity setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
        return this;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public OrderEntity setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public OrderEntity setStatus(OrderStatusEnum status) {
        this.status = status;
        return this;
    }

    public OrderActionEnum getAction() {
        return action;
    }

    public OrderEntity setAction(OrderActionEnum action) {
        this.action = action;
        return this;
    }
}
