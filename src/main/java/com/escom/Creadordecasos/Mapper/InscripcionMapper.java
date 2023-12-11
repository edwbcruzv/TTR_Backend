package com.escom.Creadordecasos.Mapper;

import com.escom.Creadordecasos.Dto.InscripcionDTO;
import com.escom.Creadordecasos.Entity.Inscripcion;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InscripcionMapper {
    InscripcionDTO toDto(Inscripcion entity);
    List<InscripcionDTO> toListDto(List<Inscripcion> list);
}
