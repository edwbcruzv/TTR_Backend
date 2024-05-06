package com.escom.CreadorPracticas.Mapper;

import com.escom.CreadorPracticas.Dto.EquipoDTO;
import com.escom.CreadorPracticas.Entity.Equipo;
import com.escom.CreadorPracticas.Entity.Estudiante;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EquipoMapper {
    @Mapping(target = "grupoId", source = "grupo.id")
    @Mapping(target = "estudiantesUsernames", source = "estudiantes")
    EquipoDTO toDto(Equipo entity);
    List<EquipoDTO> toListDto(List<Equipo> list);
    Equipo toEntity(EquipoDTO dto);

    default List<String> mapEstudiantesList(List<Estudiante> estudiantes) {
        if(estudiantes!=null) {
            return estudiantes.stream()
                    .map(Estudiante::getUsername)
                    .collect(Collectors.toList());
        }else{
            return new ArrayList<String>();
        }
    }
}
