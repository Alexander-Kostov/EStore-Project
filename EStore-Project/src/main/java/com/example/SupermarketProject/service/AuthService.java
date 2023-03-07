package com.example.SupermarketProject.service;

import com.example.SupermarketProject.mapper.UserMapper;
import com.example.SupermarketProject.model.dto.RegisterDTO;
import com.example.SupermarketProject.model.entity.User;
import com.example.SupermarketProject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    public AuthService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public boolean register(RegisterDTO registerDTO) {

        Optional<User> byEmail = this.userRepository.findByEmail(registerDTO.getEmail());

        if (byEmail.isPresent()) {
            return false;
        }

        User user = this.userMapper.dtoTOUser(registerDTO);
        this.userRepository.save(user);

        return true;
    }
}
