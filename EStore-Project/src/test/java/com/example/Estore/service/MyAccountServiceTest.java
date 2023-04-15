package com.example.Estore.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.EStore.model.dto.ChangeAccountDetailsDTO;
import com.example.EStore.model.entity.UserEntity;
import com.example.EStore.service.MyAccountService;
import com.example.EStore.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class MyAccountServiceTest {

    private UserService userService;
    private MyAccountService myAccountService;
    private UserDetails userDetails;

    @BeforeEach
    public void setup() {
        this.userService = mock(UserService.class);
        this.myAccountService = new MyAccountService(userService);
        this.userDetails = User.builder().username("test@example.com").password("password").roles("USER").build();
    }

    @Test
    public void editAccountDetailsTest() {
        ChangeAccountDetailsDTO changeAccountDetailsDTO = new ChangeAccountDetailsDTO();
        changeAccountDetailsDTO.setNewEmail("newemail@example.com");
        changeAccountDetailsDTO.setNewAddress("123 Main St.");
        changeAccountDetailsDTO.setNewFirstName("John");
        changeAccountDetailsDTO.setNewLastName("Doe");
        changeAccountDetailsDTO.setNewMobileNumber("555-1234");

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@example.com");
        when(userService.getUserByPrincipal("test@example.com")).thenReturn(userEntity);

        myAccountService.editAccountDetails(changeAccountDetailsDTO, userDetails);

        verify(userService).saveNewUserChanges(userEntity.setEmail("newemail@example.com")
                .setAddress("123 Main St.")
                .setFirstName("John")
                .setLastName("Doe")
                .setMobileNumber("555-1234"));
    }

    @Test
    public void applyChangesFromDTOToUserEntityTest() {
        ChangeAccountDetailsDTO changeAccountDetailsDTO = new ChangeAccountDetailsDTO();
        changeAccountDetailsDTO.setNewEmail("newemail@example.com");
        changeAccountDetailsDTO.setNewAddress("123 Main St.");
        changeAccountDetailsDTO.setNewFirstName("John");
        changeAccountDetailsDTO.setNewLastName("Doe");
        changeAccountDetailsDTO.setNewMobileNumber("555-1234");

        UserEntity userEntity = new UserEntity();

        UserEntity updatedUserEntity = myAccountService.applyChangesFromDTOToUserEntity(changeAccountDetailsDTO, userEntity);

        assertEquals(updatedUserEntity.getEmail(), "newemail@example.com");
        assertEquals(updatedUserEntity.getAddress(), "123 Main St.");
        assertEquals(updatedUserEntity.getFirstName(), "John");
        assertEquals(updatedUserEntity.getLastName(), "Doe");
        assertEquals(updatedUserEntity.getMobileNumber(), "555-1234");
    }
}