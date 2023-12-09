package com.escom.Creadordecasos.Mapper;

import com.escom.Creadordecasos.Dto.GrupoDTO;
import com.escom.Creadordecasos.Entity.Grupo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GrupoMapper {
    //GrupoDTO toDto(Grupo entity);
    //List<GrupoDTO> toListDto(List<Grupo> list);

}
