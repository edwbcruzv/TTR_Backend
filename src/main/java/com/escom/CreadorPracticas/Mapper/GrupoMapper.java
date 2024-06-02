package com.escom.CreadorPracticas.Mapper;

import com.escom.CreadorPracticas.Dto.GrupoDTO;
import com.escom.CreadorPracticas.Entity.*;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GrupoMapper {

    @Mapping(source = "equipos", target = "equiposIds")
    @Mapping(source = "inscripciones", target = "estudiantesUsernames")
    @Mapping(source = "profesor.nombreCompletoOrden", target = "profesorNombre")
    GrupoDTO toDto(Grupo entity);
    List<GrupoDTO> toListDto(List<Grupo> list);
    Grupo toEntity(GrupoDTO dto);

    @AfterMapping
    default void mapInscripcionesToDTO(@MappingTarget GrupoDTO dto, Grupo entity){
        dto.setProfesorNombre(entity.getProfesor().getNombreCompletoOrden());

    }
    default List<Long> map_equipos(List<Equipo> equipos) {

        if (equipos != null) {
            return equipos.stream()
                    .map(Equipo::getId)
                    .collect(Collectors.toList());
        }else{
            return new ArrayList<>();
        }
    }

    default List<String> map_inscripciones(List<Inscripcion> inscripciones) {
        if (inscripciones != null) {
            return inscripciones.stream()
                    .map(Inscripcion::getEstudiante)
                    .map(Estudiante::getUsername)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

}
