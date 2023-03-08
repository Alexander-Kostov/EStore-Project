package com.example.EStore.service;

import com.example.EStore.mapper.UserMapper;
import com.example.EStore.model.dto.LoginDTO;
import com.example.EStore.model.dto.RegisterDTO;
import com.example.EStore.model.entity.User;
import com.example.EStore.repository.UserRepository;
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

    public boolean checkForExistingEmail(String email) {
        Optional<User> byEmail = this.userRepository.findByEmail(email);

        return byEmail.isPresent();
    }



    public boolean register(RegisterDTO registerDTO) {

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())){
            return false;
        }

        User user = this.userMapper.dtoTOUser(registerDTO);
        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<User> byEmailAndPassword = this.userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());

        if(byEmailAndPassword.isEmpty()) {
            return false;
        }

        return true;
    }
}
