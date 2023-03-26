package com.example.EStore.model.entity;

import com.example.EStore.model.enums.GenderEntityEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "gender")
public class GenderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderEntityEnum genderEntityEnum;

    public long getId() {
        return id;
    }

    public GenderEntity setId(long id) {
        this.id = id;
        return this;
    }

    public GenderEntityEnum getGenderEntityEnum() {
        return genderEntityEnum;
    }

    public GenderEntity setGenderEntityEnum(GenderEntityEnum genderEntityEnum) {
        this.genderEntityEnum = genderEntityEnum;
        return this;
    }
}
