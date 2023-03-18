package com.example.EStore.service;

import com.example.EStore.model.entity.UserEntity;
import com.example.EStore.model.entity.UserRoleEntity;
import com.example.EStore.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class ApplicationUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findUserByEmail(username).map(this::mapToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found"));
    }


    public UserDetails mapToUserDetails(UserEntity userEntity){
       return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                extractAuthorities(userEntity)
        );
    }

    private List<GrantedAuthority> extractAuthorities(UserEntity userEntity) {
       return userEntity.getRoles().stream().map(this::mapRoleToGrantedAuthority).toList();
    }

    private GrantedAuthority mapRoleToGrantedAuthority(UserRoleEntity role) {
        return new SimpleGrantedAuthority("ROLE_" + role.getRole().name());
    }
}
