package com.example.EStore.model.entity;

import com.example.EStore.model.enums.ProductTypeEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "types")
public class ProductTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductTypeEnum productTypeEnum;
}
