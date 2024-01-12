package com.escom.Creadordecasos.Mapper;

import com.escom.Creadordecasos.Dto.EquipoDTO;
import com.escom.Creadordecasos.Entity.Equipo;
import com.escom.Creadordecasos.Entity.Estudiante;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EquipoMapper {
    @Mapping(target = "grupo_id", source = "grupo.id")
    @Mapping(target = "grupo_nombre", source = "grupo.nombre_grupo")
    @Mapping(target = "estudiantes_ids", source = "estudiantes")
    EquipoDTO toDto(Equipo entity);
    List<EquipoDTO> toListDto(List<Equipo> list);

    @AfterMapping   // @MappingTarget marca el objeto destino
    default void mapRecursoMultimediaLists(@MappingTarget EquipoDTO dto, Equipo entity){
        dto.setEstudiantes_ids(mapEstudiantesList(entity.getEstudiantes()));
    }

    default List<Long> mapEstudiantesList(List<Estudiante> estudiantes) {
        if(estudiantes!=null) {
            return estudiantes.stream()
                    .map(Estudiante::getId)
                    .collect(Collectors.toList());
        }else{
            return new ArrayList<Long>();
        }
    }
}
