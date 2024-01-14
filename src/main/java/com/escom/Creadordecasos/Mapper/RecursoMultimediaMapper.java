package com.escom.Creadordecasos.Mapper;

import com.escom.Creadordecasos.Dto.RecursoMultimediaDTO;
import com.escom.Creadordecasos.Entity.RecursoMultimedia;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RecursoMultimediaMapper {
    RecursoMultimediaDTO toDto(RecursoMultimedia entity);
    List<RecursoMultimediaDTO> toListDto(List<RecursoMultimedia> list);
    //RecursoMultimedia toEntity(RecursoMultimediaDTO dto);
}
