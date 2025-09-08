package com.read.dima.Mappers;

import com.read.dima.LibDTO.UserDTO;
import com.read.dima.Models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "id", target = "id")
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);

}
