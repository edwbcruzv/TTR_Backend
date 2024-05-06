package com.escom.CreadorPracticas.Mapper;

import com.escom.CreadorPracticas.Dto.GrupoDTO;
import com.escom.CreadorPracticas.Entity.Equipo;
import com.escom.CreadorPracticas.Entity.Grupo;
import com.escom.CreadorPracticas.Entity.Inscripcion;
import com.escom.CreadorPracticas.Entity.Profesor;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GrupoMapper {

    @Mapping(source = "equipos", target = "equiposIds")
    @Mapping(source = "profesor", target = "profesorNombre")
    GrupoDTO toDto(Grupo entity);
    List<GrupoDTO> toListDto(List<Grupo> list);
    Grupo toEntity(GrupoDTO dto);

    @AfterMapping
    default void mapInscripcionesToDTO(@MappingTarget GrupoDTO dto, Grupo entity){
        dto.setProfesorNombre(concatenarNombresProfesor(entity.getProfesor()));
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

    default String concatenarNombresProfesor(Profesor persona) {
        return persona.getNombre() + " " + persona.getApellidoPaterno() + " " + persona.getApellidoMaterno();
    }

}
