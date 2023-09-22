package com.escom.Creadordecasos.Mapper;

import com.escom.Creadordecasos.Dto.UserDto;
import com.escom.Creadordecasos.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Interfaz utilizada para mappear los objetos User y UserDto
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDto toUserDto(User user);

    User toUserEntity(UserDto userDto);
}
