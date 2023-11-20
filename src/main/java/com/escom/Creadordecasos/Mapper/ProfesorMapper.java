package com.escom.Creadordecasos.Mapper;

import com.escom.Creadordecasos.Dto.ProfesorDTO;
import com.escom.Creadordecasos.Entity.Profesor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProfesorMapper {

    ProfesorDTO toDto(Profesor profesor);
    List<ProfesorDTO> toListDto(List<Profesor> profesorList);

}
