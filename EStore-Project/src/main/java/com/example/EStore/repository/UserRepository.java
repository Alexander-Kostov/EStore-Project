package com.example.EStore.repository;

import com.example.EStore.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserByEmail(String email);

    Optional<UserEntity> findByEmailAndPassword(String Email, String password);
}
