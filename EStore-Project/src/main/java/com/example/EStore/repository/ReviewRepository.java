package com.example.EStore.repository;

import com.example.EStore.model.entity.ProductEntity;
import com.example.EStore.model.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    List<ReviewEntity> findByProduct(ProductEntity product);

}
