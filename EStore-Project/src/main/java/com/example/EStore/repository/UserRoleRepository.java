package com.example.EStore.repository;

import com.example.EStore.model.enums.UserRoleEnum;
import com.example.EStore.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findUserRoleEntitiesByRole(UserRoleEnum role);
}
