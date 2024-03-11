package com.escom.Creadordecasos.Mapper;

import com.escom.Creadordecasos.Dto.EquipoDTO;
import com.escom.Creadordecasos.Dto.RecursoMultimediaDTO;
import com.escom.Creadordecasos.Entity.Equipo;
import com.escom.Creadordecasos.Entity.Estudiante;
import com.escom.Creadordecasos.Entity.RecursoMultimedia;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EquipoMapper {
    EquipoDTO toDto(Equipo entity);
    List<EquipoDTO> toListDto(List<Equipo> list);
    Equipo toEntity(EquipoDTO dto);
}
