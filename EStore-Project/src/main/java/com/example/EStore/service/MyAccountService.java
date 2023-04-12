package com.example.EStore.service;

import com.example.EStore.model.dto.ChangeAccountDetailsDTO;
import com.example.EStore.model.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class MyAccountService {

    private UserService userService;

    public MyAccountService(UserService userService) {
        this.userService = userService;
    }

    public void editAccountDetails(ChangeAccountDetailsDTO changeAccountDetailsDTO, UserDetails principal) {
        UserEntity userByPrincipal = this.userService.getUserByPrincipal(principal.getUsername());

        UserEntity userWithChanges = applyChangesFromDTOToUserEntity(changeAccountDetailsDTO, userByPrincipal);

        this.userService.saveNewUserChanges(userWithChanges);


    }

    public UserEntity applyChangesFromDTOToUserEntity(ChangeAccountDetailsDTO changeAccountDetailsDTO, UserEntity userEntity) {
      return userEntity.setEmail(changeAccountDetailsDTO.getNewEmail())
                .setAddress(changeAccountDetailsDTO.getNewAddress())
                .setFirstName(changeAccountDetailsDTO.getNewFirstName())
                .setLastName(changeAccountDetailsDTO.getNewLastName())
                .setMobileNumber(changeAccountDetailsDTO.getNewMobileNumber());
    }
}
