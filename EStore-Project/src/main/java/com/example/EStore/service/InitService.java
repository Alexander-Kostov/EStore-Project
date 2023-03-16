package com.example.EStore.service;

import com.example.EStore.model.dto.enums.UserRoleEnum;
import com.example.EStore.model.entity.UserRoleEntity;
import com.example.EStore.repository.UserRepository;
import com.example.EStore.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class InitService {

    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;

    public InitService(UserRoleRepository userRoleRepository, UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        initRoles();
    }

    private void initRoles() {
        if (userRepository.count() == 0) {
            var moderatorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);
            var adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);

            userRoleRepository.save(moderatorRole);
            userRoleRepository.save(adminRole);
        }
    }
}
