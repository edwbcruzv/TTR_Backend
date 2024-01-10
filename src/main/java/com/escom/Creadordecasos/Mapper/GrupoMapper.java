package com.escom.Creadordecasos.Mapper;

import com.escom.Creadordecasos.Dto.GrupoDTO;
import com.escom.Creadordecasos.Entity.Equipo;
import com.escom.Creadordecasos.Entity.Grupo;
import com.escom.Creadordecasos.Entity.Inscripcion;
import com.escom.Creadordecasos.Entity.Profesor;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GrupoMapper {

    @Mapping(target = "equipos_ids",source = "equipos")
    @Mapping(target = "inscripciones_ids",source = "inscripciones")
    @Mapping(target = "profesor_nombre", source = "profesor")
    GrupoDTO toDto(Grupo entity);
    List<GrupoDTO> toListDto(List<Grupo> list);


    @AfterMapping
    default void mapInscripcionesToDTO(@MappingTarget GrupoDTO dto, Grupo entity){
        dto.setProfesor_nombre(concatenarNombresProfesor(entity.getProfesor()));
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
    default List<Long> map_inscripciones(List<Inscripcion> inscripciones) {
        if (inscripciones!=null) {
            return inscripciones.stream()
                    .map(Inscripcion::getId)
                    .collect(Collectors.toList());
        }else{
            return new ArrayList<>();
        }
    }

    default String concatenarNombresProfesor(Profesor persona) {
        return persona.getNombre() + " " + persona.getApellido_paterno() + " " + persona.getApellido_materno();
    }

}
