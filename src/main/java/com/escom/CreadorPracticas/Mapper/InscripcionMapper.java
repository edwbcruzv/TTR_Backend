package com.escom.CreadorPracticas.Mapper;

import com.escom.CreadorPracticas.Dto.GrupoDTO;
import com.escom.CreadorPracticas.Dto.InscripcionDTO;
import com.escom.CreadorPracticas.Entity.Estudiante;
import com.escom.CreadorPracticas.Entity.Grupo;
import com.escom.CreadorPracticas.Entity.Inscripcion;
import com.escom.CreadorPracticas.Entity.Profesor;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InscripcionMapper {

    @Mapping(source = "inscripcionKey.estudiante_username", target = "estudianteUsername")
    @Mapping(source = "inscripcionKey.grupo_id", target = "grupoId")
    @Mapping(source = "estudiante", target = "estudianteNombre")
    @Mapping(source = "grupo.profesor", target = "profesorNombre")
    @Mapping(source = "grupo.nombre", target = "grupoNombre")
    @Mapping(source = "grupo.clave", target = "grupoClave")
    InscripcionDTO toDto(Inscripcion entity);
    List<InscripcionDTO> toListDto(List<Inscripcion> list);
    Inscripcion toEntity(InscripcionDTO dto);
    @AfterMapping
    default void mapInscripcionesToDTO(@MappingTarget InscripcionDTO dto, Inscripcion entity){
        dto.setEstudianteNombre(concatenarNombresEstudiante(entity.getEstudiante()));
        dto.setProfesorNombre(concatenarNombresProfesor(entity.getGrupo().getProfesor()));
    }

    default String concatenarNombresEstudiante(Estudiante persona) {
        return persona.getNombre() + " " + persona.getApellidoPaterno() + " " + persona.getApellidoMaterno();
    }

    default String concatenarNombresProfesor(Profesor persona) {
        return persona.getNombre() + " " + persona.getApellidoPaterno() + " " + persona.getApellidoMaterno();
    }
}

