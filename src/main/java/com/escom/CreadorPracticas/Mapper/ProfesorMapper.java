package com.escom.CreadorPracticas.Mapper;

import com.escom.CreadorPracticas.Dto.ProfesorDTO;
import com.escom.CreadorPracticas.Entity.Profesor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProfesorMapper {

    ProfesorDTO toDto(Profesor profesor);
    List<ProfesorDTO> toListDto(List<Profesor> profesorList);

}
