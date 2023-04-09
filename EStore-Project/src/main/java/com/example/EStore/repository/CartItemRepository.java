package com.example.EStore.repository;

import com.example.EStore.model.entity.CartItemEntity;
import com.example.EStore.model.entity.OrderedProductEntity;
import com.example.EStore.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    public List<CartItemEntity> findByCustomer(UserEntity user);

    CartItemEntity findByCustomerAndOrderedProduct(UserEntity customer, OrderedProductEntity orderedProductEntity);

    void deleteByOrderedProductId(long id);
}
