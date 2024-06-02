package com.escom.CreadorPracticas.Mapper;

import com.escom.CreadorPracticas.Dto.SolucionDTO;
import com.escom.CreadorPracticas.Dto.SolucionMinDTO;
import com.escom.CreadorPracticas.Entity.Estudiante;
import com.escom.CreadorPracticas.Entity.Profesor;
import com.escom.CreadorPracticas.Entity.Solucion;
import org.mapstruct.*;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolucionMapper {
    SolucionDTO toDto(Solucion entity);
    List<SolucionDTO> toListDto(List<Solucion> list);

    List<SolucionMinDTO> toListMinDto(List<Solucion> list);
    Solucion toEntity(SolucionDTO dto);

    @Mapping(target = "practicaTitulo", source = "practica.titulo")
    @Mapping(target = "practicaId", source = "practica.id")
    @Mapping(target = "practicaDescripcion", source = "practica.descripcion")
    @Mapping(target = "estudianteNombre", source = "estudiante.nombreCompletoOrden")
    @Mapping(target = "equipoNombre", source = "equipo.nombre")
    SolucionMinDTO toMinDto(Solucion entity);

    @AfterMapping
    default void map1(@MappingTarget SolucionMinDTO dto, Solucion entity) {
        Optional.ofNullable(entity.getEquipo())
                .ifPresentOrElse(
                        equipo -> dto.setEquipoNombre(equipo.getNombre()),
                        () -> dto.setEquipoNombre(null)
                );

        Optional.ofNullable(entity.getEstudiante())
                .ifPresentOrElse(
                        estudiante -> dto.setEstudianteNombre(estudiante.getNombreCompletoOrden()),
                        () -> dto.setEstudianteNombre(null)
                );
    }
}
