package com.escom.Creadordecasos.Mapper;

import com.escom.Creadordecasos.Dto.InscripcionDTO;
import com.escom.Creadordecasos.Entity.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InscripcionMapper {

    @Mapping(target = "estudiante_nombre", source = "estudiante")
    @Mapping(target = "grupo_nombre", source = "grupo")
    @Mapping(target = "clave", source = "grupo.clave")
    @Mapping(target = "profesor_nombre", source = "grupo.profesor")
    InscripcionDTO toDto(Inscripcion entity);

    @AfterMapping
    default void mapInscripcionesToDTO(@MappingTarget InscripcionDTO dto, Inscripcion entity) {
        Profesor profesor = entity.getGrupo().getProfesor();
        System.out.println("Ejecutando Mapper Inscripcion.");
        dto.setEstudiante_nombre(concatenarNombresEstudiante(entity.getEstudiante()));
        dto.setProfesor_nombre(concatenarNombresProfesor(profesor));
        dto.setGrupo_nombre(concatenarNombresGrupo(entity.getGrupo()));
    }

    default String concatenarNombresProfesor(Profesor persona) {
        return persona.getNombre() + " " + persona.getApellido_paterno() + " " + persona.getApellido_materno();
    }

    default String concatenarNombresEstudiante(Estudiante persona) {
        return persona.getNombre() + "-" + persona.getApellido_paterno() + "-" + persona.getApellido_materno();
    }

    default String concatenarNombresGrupo(Grupo grupo) {
        return grupo.getNombre_grupo() + " : " + grupo.getNombre_materia();
    }

    List<InscripcionDTO> toListDto(List<Inscripcion> list);
}

