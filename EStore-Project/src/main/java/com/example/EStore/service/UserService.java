package com.example.EStore.service;

import com.example.EStore.model.dto.RegisterDTO;
import com.example.EStore.model.entity.UserEntity;
import com.example.EStore.model.entity.UserRoleEntity;
import com.example.EStore.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public void registerUser(RegisterDTO registerDTO,
                             Consumer<Authentication> successfulLoginProcessor) {

        UserEntity userEntity = new UserEntity()
                .setFirstName(registerDTO.getFirstName())
                .setLastName(registerDTO.getLastName())
                .setPassword(passwordEncoder.encode(registerDTO.getPassword()))
                .setEmail(registerDTO.getEmail())
                .setAddress(registerDTO.getAddress())
                .setMobileNumber(registerDTO.getMobileNumber());

        this.userRepository.save(userEntity);

        UserDetails userDetails = userDetailsService.loadUserByUsername(registerDTO.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        successfulLoginProcessor.accept(authentication);

    }

    public boolean checkForUsedEmail(String email) {

        Optional<UserEntity> userByEmail = this.userRepository.findUserByEmail(email);

        return userByEmail.isPresent();
    }

    public UserEntity getUserByPrincipal(String email) {
        return this.userRepository.findUserByEmail(email).get();
    }

    public Optional<UserEntity> getUserByEmail(String username) {
        return this.userRepository.findUserByEmail(username);
    }
}
