package com.escom.Creadordecasos.Mapper;

import com.escom.Creadordecasos.Dto.CasoEstudioDTO;
import com.escom.Creadordecasos.Entity.*;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CasoEstudioMapper {


    @Mapping(target = "resumen_multimedia_list", source = "resumen_multimedia_list")
    @Mapping(target = "objetivos_multimedia_list", source = "objetivos_multimedia_list")
    @Mapping(target = "clasificacion_multimedia_list", source = "clasificacion_multimedia_list")
    @Mapping(target = "lugar_multimedia_list", source = "lugar_multimedia_list")
    @Mapping(target = "temporalidades_multimedia_list", source = "temporalidades_multimedia_list")
    @Mapping(target = "protagonistas_multimedia_list", source = "protagonistas_multimedia_list")
    @Mapping(target = "organizaciones_multimedia_list", source = "organizaciones_multimedia_list")
    @Mapping(target = "preguntas_multimedia_list", source = "preguntas_multimedia_list")
    @Mapping(target = "riesgos_multimedia_list", source = "riesgos_multimedia_list")
    @Mapping(target = "resultados_multimedia_list", source = "resultados_multimedia_list")
    @Mapping(target = "anexos_multimedia_list", source = "anexos_multimedia_list")
    @Mapping(target = "profesores", source = "profesores")
    @Mapping(target = "equipos", source = "equipos")
    @Mapping(target = "profesor_id",source = "profesor.id")
    CasoEstudioDTO toDto(CasoEstudio entity);

    List<CasoEstudioDTO> toListDto(List<CasoEstudio> list);

    @AfterMapping   // @MappingTarget marca el objeto destino
    default void mapRecursoMultimediaLists(@MappingTarget CasoEstudioDTO casoEstudioDTO, CasoEstudio casoEstudio) {
        casoEstudioDTO.setResumen_multimedia_list(mapRecursoMultimediaList(casoEstudio.getResumen_multimedia_list()));
        casoEstudioDTO.setObjetivos_multimedia_list(mapRecursoMultimediaList(casoEstudio.getObjetivos_multimedia_list()));
        casoEstudioDTO.setClasificacion_multimedia_list(mapRecursoMultimediaList(casoEstudio.getClasificacion_multimedia_list()));
        casoEstudioDTO.setLugar_multimedia_list(mapRecursoMultimediaList(casoEstudio.getLugar_multimedia_list()));
        casoEstudioDTO.setTemporalidades_multimedia_list(mapRecursoMultimediaList(casoEstudio.getTemporalidades_multimedia_list()));
        casoEstudioDTO.setProtagonistas_multimedia_list(mapRecursoMultimediaList(casoEstudio.getProtagonistas_multimedia_list()));
        casoEstudioDTO.setOrganizaciones_multimedia_list(mapRecursoMultimediaList(casoEstudio.getOrganizaciones_multimedia_list()));
        casoEstudioDTO.setPreguntas_multimedia_list(mapRecursoMultimediaList(casoEstudio.getPreguntas_multimedia_list()));
        casoEstudioDTO.setRiesgos_multimedia_list(mapRecursoMultimediaList(casoEstudio.getRiesgos_multimedia_list()));
        casoEstudioDTO.setResultados_multimedia_list(mapRecursoMultimediaList(casoEstudio.getResultados_multimedia_list()));
        casoEstudioDTO.setAnexos_multimedia_list(mapRecursoMultimediaList(casoEstudio.getAnexos_multimedia_list()));

        casoEstudioDTO.setProfesores(mapProfesores(casoEstudio.getProfesores()));
        casoEstudioDTO.setEquipos(mapEquipos(casoEstudio.getEquipos()));
    }

    default List<Long> mapRecursoMultimediaList(List<RecursoMultimedia> recursos) {
        if(recursos!=null) {
            System.out.println("diferente de nullo");
            return recursos.stream()
                    .map(RecursoMultimedia::getId)
                    .collect(Collectors.toList());
        }else{
            return new ArrayList<Long>();
        }
    }

    default List<Long> mapProfesores(List<Profesor> profesores) {
        if(profesores!=null) {
            return profesores.stream()
                    .map(Profesor::getId)
                    .collect(Collectors.toList());
        }else{
            return new ArrayList<Long>();
        }
    }

    default List<Long> mapEquipos(List<Equipo> equipo) {
        if(equipo!=null) {
                return equipo.stream()
                        .map(Equipo::getId)
                        .collect(Collectors.toList());
        }else{
               return new ArrayList<Long>();
            }
    }
}
