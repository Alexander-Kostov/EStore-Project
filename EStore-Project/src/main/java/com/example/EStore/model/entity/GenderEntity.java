package com.example.EStore.model.entity;

import com.example.EStore.model.enums.GenderEntityEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "product_gender")
public class GenderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderEntityEnum gender;

    public GenderEntity() {
    }

    public GenderEntity(GenderEntityEnum gender) {
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public GenderEntity setId(long id) {
        this.id = id;
        return this;
    }

    public GenderEntityEnum getGender() {
        return gender;
    }

    public GenderEntity setGender(GenderEntityEnum genderEntityEnum) {
        this.gender = genderEntityEnum;
        return this;
    }
}
