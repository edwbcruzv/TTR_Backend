package com.escom.Creadordecasos.Mapper;

import com.escom.Creadordecasos.Dto.MensajeDTO;
import com.escom.Creadordecasos.Entity.Mensaje;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MensajeMapper {

    MensajeDTO toDto(Mensaje entity);
    List<MensajeDTO> toListDto(List<Mensaje> list);


}
