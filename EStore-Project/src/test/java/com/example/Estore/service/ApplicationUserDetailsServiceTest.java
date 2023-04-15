package com.example.Estore.service;

import com.example.EStore.model.entity.UserEntity;
import com.example.EStore.model.entity.UserRoleEntity;
import com.example.EStore.model.enums.UserRoleEnum;
import com.example.EStore.repository.UserRepository;
import com.example.EStore.service.ApplicationUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicationUserDetailsServiceTest {

    private ApplicationUserDetailsService toTest;

    private final String EXISTING_EMAIL = "admin@example.com";
    private final String NON_EXISTING_EMAIL = "admin@example.com";

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new ApplicationUserDetailsService(
                mockUserRepository
        );
    }

    @Test
    void testUserFound(){

        UserRoleEntity testAdminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity testModeratorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);

        UserEntity testUserEntity = new UserEntity()
                .setEmail(EXISTING_EMAIL)
                .setPassword("topsecret")
                .setRoles(List.of(testAdminRole, testModeratorRole));

        when(mockUserRepository.findUserByEmail(EXISTING_EMAIL))
                .thenReturn(Optional.of(testUserEntity));

        UserDetails adminDetails = toTest.loadUserByUsername(EXISTING_EMAIL);


        Assertions.assertNotNull(adminDetails);
        Assertions.assertEquals(EXISTING_EMAIL, adminDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getPassword(), adminDetails.getPassword());
        Assertions.assertEquals(2, adminDetails.getAuthorities().size());
        assertRole(adminDetails.getAuthorities(), "ROLE_ADMIN");
        assertRole(adminDetails.getAuthorities(), "ROLE_MODERATOR");


    }

    private void assertRole(Collection<? extends GrantedAuthority> authorities,
                            String role) {
    authorities.stream()
            .filter(a -> role.equals(a.getAuthority()))
            .findAny()
            .orElseThrow(() -> new AssertionFailedError(("Role " + role + " not found!")));
    }

    @Test
    void testUserNotFound(){
        assertThrows(UsernameNotFoundException.class,
                () ->{
            toTest.loadUserByUsername(NON_EXISTING_EMAIL);
        });
    }
}
