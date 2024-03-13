package com.escom.CreadorPracticas.Mapper;

import com.escom.CreadorPracticas.Dto.EquipoDTO;
import com.escom.CreadorPracticas.Entity.Equipo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EquipoMapper {
    EquipoDTO toDto(Equipo entity);
    List<EquipoDTO> toListDto(List<Equipo> list);
    Equipo toEntity(EquipoDTO dto);
}
