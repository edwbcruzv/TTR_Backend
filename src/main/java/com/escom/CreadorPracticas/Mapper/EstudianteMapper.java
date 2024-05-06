package com.escom.CreadorPracticas.Mapper;

import com.escom.CreadorPracticas.Dto.EstudianteDTO;
import com.escom.CreadorPracticas.Entity.Estudiante;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EstudianteMapper {

    EstudianteDTO toDto(Estudiante entity);
    List<EstudianteDTO> toListDto(List<Estudiante> list);


}
