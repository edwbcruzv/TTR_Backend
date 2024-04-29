package com.escom.CreadorPracticas.Mapper;

import com.escom.CreadorPracticas.Dto.InscripcionDTO;
import com.escom.CreadorPracticas.Entity.Inscripcion;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InscripcionMapper {

    @Mapping(source = "inscripcionKey.estudiante_username", target = "estudianteUsername")
    @Mapping(source = "inscripcionKey.grupo_id", target = "grupoId")
    InscripcionDTO toDto(Inscripcion entity);
    List<InscripcionDTO> toListDto(List<Inscripcion> list);
    Inscripcion toEntity(InscripcionDTO dto);
}

