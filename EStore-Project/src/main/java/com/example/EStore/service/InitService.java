package com.example.EStore.service;

import com.example.EStore.model.enums.UserRoleEnum;
import com.example.EStore.model.entity.UserEntity;
import com.example.EStore.model.entity.UserRoleEntity;
import com.example.EStore.repository.UserRepository;
import com.example.EStore.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitService {

    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitService(UserRoleRepository userRoleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, @Value("${app.default.password}") String defaultPassword) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initUsers();
    }

    private void initRoles() {
        if (userRepository.count() == 0) {
            var moderatorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);
            var adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);

            userRoleRepository.save(moderatorRole);
            userRoleRepository.save(adminRole);
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            initAdmin();
            initModerator();
            initNormalUser();
        }
    }

    private void initAdmin() {
        var admin = new UserEntity().setAddress("W 52th str")
                .setEmail("admin@example.com")
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setPassword(passwordEncoder.encode("topsecret"))
                .setMobileNumber("+359874479102")
                .setRoles(userRoleRepository.findAll());

        userRepository.save(admin);
    }

    private void initModerator(){

        var moderatorRole = userRoleRepository.findUserRoleEntitiesByRole(UserRoleEnum.MODERATOR).orElseThrow();

        var moderator = new UserEntity().setAddress("W 52th str")
                .setEmail("moderator@example.com")
                .setFirstName("Moderator")
                .setLastName("Moderatorov")
                .setPassword(passwordEncoder.encode("topsecret"))
                .setMobileNumber("+359874479102")
                .setRoles(List.of(moderatorRole));

        userRepository.save(moderator);
    }

    private void initNormalUser() {
        var user = new UserEntity().setAddress("W 52th str")
                .setEmail("user@example.com")
                .setFirstName("User")
                .setLastName("Userov")
                .setPassword(passwordEncoder.encode("topsecret"))
                .setMobileNumber("+359874479102");

        userRepository.save(user);

    }
}
