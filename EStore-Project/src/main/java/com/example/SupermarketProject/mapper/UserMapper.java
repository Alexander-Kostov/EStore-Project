package com.example.SupermarketProject.mapper;

import com.example.SupermarketProject.model.dto.RegisterDTO;
import com.example.SupermarketProject.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    RegisterDTO userTODto(User user);

    @Mapping(target = "orders", ignore = true)
    User dtoTOUser(RegisterDTO registerDTO);


}
