package com.example.EStore.repository;

import com.example.EStore.model.entity.ProductSizeEntity;
import com.example.EStore.model.enums.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, Long> {
    Optional<ProductSizeEntity> findByProductSize(ProductSize size);
}
