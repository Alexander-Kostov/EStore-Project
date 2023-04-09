package com.example.EStore.repository;

import com.example.EStore.model.entity.OrderEntity;
import com.example.EStore.model.entity.OrderedProductEntity;
import com.example.EStore.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findItemsByCustomerId(long id);
}
