package com.example.EStore.service;

import com.example.EStore.ProductTypeRepository.ProductTypeRepository;
import com.example.EStore.model.entity.GenderEntity;
import com.example.EStore.model.entity.ProductTypeEntity;
import com.example.EStore.model.enums.GenderEntityEnum;
import com.example.EStore.model.enums.ProductTypeEnum;
import com.example.EStore.model.enums.UserRoleEnum;
import com.example.EStore.model.entity.UserEntity;
import com.example.EStore.model.entity.UserRoleEntity;
import com.example.EStore.repository.GenderRepository;
import com.example.EStore.repository.UserRepository;
import com.example.EStore.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InitService {

    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private String defaultPassword;
    private GenderRepository genderRepository;

    private ProductTypeRepository productTypeRepository;

    public InitService(UserRoleRepository userRoleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder,
                       @Value("${app.admin.defaultpass}") String defaultPassword, GenderRepository genderRepository, ProductTypeRepository productTypeRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultPassword = defaultPassword;
        this.genderRepository = genderRepository;
        this.productTypeRepository = productTypeRepository;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initUsers();
        initGenders();
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
            initProductTypes();
        }
    }
    private void initAdmin() {
        var admin = new UserEntity().setAddress("W 52th str")
                .setEmail("admin@example.com")
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setPassword(passwordEncoder.encode(defaultPassword))
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

    private void initGenders() {
        if (this.genderRepository.count() == 0) {
            List<GenderEntity> genders = Arrays.stream(GenderEntityEnum.values()).map(GenderEntity::new).collect(Collectors.toList());

            this.genderRepository.saveAll(genders);
        }
    }

    private void initProductTypes() {
        if (this.productTypeRepository.count() == 0) {
            List<ProductTypeEntity> productTypes = Arrays.stream(ProductTypeEnum.values()).map(ProductTypeEntity::new).collect(Collectors.toList());

            this.productTypeRepository.saveAll(productTypes);
        }
    }

}
