package com.escom.CreadorPracticas.Mapper;

import com.escom.CreadorPracticas.Dto.PracticaDTO;
import com.escom.CreadorPracticas.Entity.Practica;
import com.escom.CreadorPracticas.Entity.Profesor;
import com.escom.CreadorPracticas.Entity.RecursoMultimedia;
import com.escom.CreadorPracticas.Entity.Solucion;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PracticaMapper {

    @Mapping(target = "recursosMultimedia", source = "recursosMultimedia")
    @Mapping(target = "soluciones", source = "soluciones")
    @Mapping(target = "nombreProfesor", source = "profesor")
    @Mapping(target = "usernameProfesor", source = "profesor.username")
    PracticaDTO toDto(Practica entity);
    List<PracticaDTO> toListDto(List<Practica> list);
    //Practica toEntity(PracticaDTO dto);

    @AfterMapping   // @MappingTarget marca el objeto destino
    default void mapRecursoMultimediaLists(@MappingTarget PracticaDTO dto, Practica entity){
        dto.setRecursosMultimedia(mapRecursoMultimediaList(entity.getRecursosMultimedia()));
        dto.setSoluciones(mapSolucionList(entity.getSoluciones()));
        dto.setNombreProfesor(concatenarNombresProfesor(entity.getProfesor()));
    }

    default List<Long> mapRecursoMultimediaList(List<RecursoMultimedia> recursos) {
        if(recursos!=null) {
            return recursos.stream()
                    .map(RecursoMultimedia::getId)
                    .collect(Collectors.toList());
        }else{
            return new ArrayList<Long>();
        }
    }

    default List<Long> mapSolucionList(List<Solucion> soluciones) {
        if(soluciones!=null) {
            return soluciones.stream()
                    .map(Solucion::getId)
                    .collect(Collectors.toList());
        }else{
            return new ArrayList<Long>();
        }
    }

    default String concatenarNombresProfesor(Profesor persona) {
        return persona.getNombre() + " " + persona.getApellidoPaterno() + " " + persona.getApellidoMaterno();
    }
}
