package com.escom.Creadordecasos.Mapper;

import com.escom.Creadordecasos.Dto.EquipoDTO;
import com.escom.Creadordecasos.Entity.Equipo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EquipoMapper {
    EquipoDTO toDto(Equipo entity);
    List<EquipoDTO> toListDto(List<Equipo> list);
}
