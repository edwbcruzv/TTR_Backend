package com.escom.CreadorPracticas.Mapper;

import com.escom.CreadorPracticas.Dto.GrupoDTO;
import com.escom.CreadorPracticas.Entity.Grupo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GrupoMapper {
    GrupoDTO toDto(Grupo entity);
    List<GrupoDTO> toListDto(List<Grupo> list);
    Grupo toEntity(GrupoDTO dto);

}
