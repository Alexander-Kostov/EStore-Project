package com.example.EStore.repository;

import com.example.EStore.model.entity.GenderEntity;
import com.example.EStore.model.enums.GenderEntityEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenderRepository extends JpaRepository<GenderEntity, Long> {
    GenderEntity findByGender(GenderEntityEnum gender);
}
