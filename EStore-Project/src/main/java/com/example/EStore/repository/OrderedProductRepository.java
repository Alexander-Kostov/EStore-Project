package com.example.EStore.repository;

import com.example.EStore.model.entity.OrderedProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProductEntity, Long> {
    OrderedProductEntity findByProductId(Long id);
    List<OrderedProductEntity> findByCustomerId(long id);
}
