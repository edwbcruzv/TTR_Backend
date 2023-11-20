package com.escom.Creadordecasos.Mapper;


import com.escom.Creadordecasos.Dto.UsuarioDTO;
import com.escom.Creadordecasos.Entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    UsuarioDTO toDto(Usuario usuario);
    List<UsuarioDTO> toListDto(List<Usuario> usuarioList);

    Usuario toEntity(UsuarioDTO usuarioDto);

}
