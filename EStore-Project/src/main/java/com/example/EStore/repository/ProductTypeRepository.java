package com.example.EStore.repository;

import com.example.EStore.model.entity.GenderEntity;
import com.example.EStore.model.entity.ProductTypeEntity;
import com.example.EStore.model.enums.GenderEntityEnum;
import com.example.EStore.model.enums.ProductTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Long> {

    ProductTypeEntity findByProductType(ProductTypeEnum productEnum);
}
