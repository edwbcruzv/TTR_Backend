package com.escom.CreadorPracticas.Mapper;

import com.escom.CreadorPracticas.Dto.RecursoMultimediaDTO;
import com.escom.CreadorPracticas.Entity.RecursoMultimedia;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RecursoMultimediaMapper {
    RecursoMultimediaDTO toDto(RecursoMultimedia entity);
    List<RecursoMultimediaDTO> toListDto(List<RecursoMultimedia> list);
    //RecursoMultimedia toEntity(RecursoMultimediaDTO dto);
}
