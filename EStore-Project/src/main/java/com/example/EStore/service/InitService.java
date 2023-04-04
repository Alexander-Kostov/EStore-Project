package com.example.EStore.service;

import com.example.EStore.model.entity.*;
import com.example.EStore.model.enums.ProductSize;
import com.example.EStore.repository.*;
import com.example.EStore.model.enums.GenderEntityEnum;
import com.example.EStore.model.enums.ProductTypeEnum;
import com.example.EStore.model.enums.UserRoleEnum;
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

    private ProductSizeRepository productSizeRepository;

    public InitService(UserRoleRepository userRoleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder,
                       @Value("${app.admin.defaultpass}") String defaultPassword, GenderRepository genderRepository, ProductTypeRepository productTypeRepository, ProductSizeRepository productSizeRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultPassword = defaultPassword;
        this.genderRepository = genderRepository;
        this.productTypeRepository = productTypeRepository;
        this.productSizeRepository = productSizeRepository;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initUsers();
        initGenders();
        initProductTypes();
        initProductSizes();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
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

    private void initProductSizes() {
        if(this.productSizeRepository.count() == 0) {
            List<ProductSizeEntity> productSizes = Arrays.stream(ProductSize.values()).map(ProductSizeEntity::new).collect(Collectors.toList());

            this.productSizeRepository.saveAll(productSizes);
        }
    }

}
