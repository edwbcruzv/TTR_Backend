package com.escom.Creadordecasos.Mapper;

import com.escom.Creadordecasos.Dto.GrupoDTO;
import com.escom.Creadordecasos.Entity.Equipo;
import com.escom.Creadordecasos.Entity.Grupo;
import com.escom.Creadordecasos.Entity.Inscripcion;
import com.escom.Creadordecasos.Entity.Profesor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GrupoMapper {
    @Mapping(target = "profesor_id",source = "profesor.id")
    @Mapping(target = "equipos_ids",source = "equipos")
    @Mapping(target = "inscripciones_ids",source = "inscripciones")
    GrupoDTO toDto(Grupo entity);
    List<GrupoDTO> toListDto(List<Grupo> list);

    default Long map(Profesor profesor) {
        return profesor != null ? profesor.getId() : null;
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

}
