package com.escom.CreadorPracticas.Mapper;

import com.escom.CreadorPracticas.Dto.SolucionDTO;
import com.escom.CreadorPracticas.Entity.Solucion;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolucionMapper {
    SolucionDTO toDto(Solucion entity);
    List<SolucionDTO> toListDto(List<Solucion> list);
    Solucion toEntity(SolucionDTO dto);
}
