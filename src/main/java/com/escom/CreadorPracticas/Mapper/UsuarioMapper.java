package com.escom.CreadorPracticas.Mapper;


import com.escom.CreadorPracticas.Dto.UsuarioDTO;
import com.escom.CreadorPracticas.Entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    UsuarioDTO toDto(Usuario usuario);
    List<UsuarioDTO> toListDto(List<Usuario> usuarioList);

    Usuario toEntity(UsuarioDTO usuarioDto);

}
