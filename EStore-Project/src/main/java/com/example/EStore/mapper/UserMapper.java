package com.example.EStore.mapper;

import com.example.EStore.model.dto.RegisterDTO;
import com.example.EStore.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    RegisterDTO userTODto(UserEntity userEntity);

    @Mapping(target = "orders", ignore = true)
    UserEntity dtoTOUser(RegisterDTO registerDTO);


}
