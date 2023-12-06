package com.escom.Creadordecasos.Mapper;

import com.escom.Creadordecasos.Dto.CasoEstudioDTO;
import com.escom.Creadordecasos.Entity.CasoEstudio;
import com.escom.Creadordecasos.Entity.Grupo;
import com.escom.Creadordecasos.Entity.Profesor;
import com.escom.Creadordecasos.Entity.RecursoMultimedia;
import com.escom.Creadordecasos.Repository.Profesores.ProfesorRepository;
import com.escom.Creadordecasos.Service.CasosEstudio.Bodies.CasoEstudioReq;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CasoEstudioMapper {


    @Mapping(target = "resumen_multimedia_list", ignore = true)
    @Mapping(target = "objetivos_multimedia_list", ignore = true)
    @Mapping(target = "clasificacion_multimedia_list", ignore = true)
    @Mapping(target = "lugar_multimedia_list", ignore = true)
    @Mapping(target = "temporalidades_multimedia_list", ignore = true)
    @Mapping(target = "protagonistas_multimedia_list", ignore = true)
    @Mapping(target = "organizaciones_multimedia_list", ignore = true)
    @Mapping(target = "preguntas_multimedia_list", ignore = true)
    @Mapping(target = "riesgos_multimedia_list", ignore = true)
    @Mapping(target = "resultados_multimedia_list", ignore = true)
    @Mapping(target = "anexos_multimedia_list", ignore = true)
    @Mapping(target = "profesores", ignore = true)
    @Mapping(target = "grupos", ignore = true)
    CasoEstudioDTO toDto(CasoEstudio entity);

    //CasoEstudio toEntity(CasoEstudioReq req);

    List<CasoEstudioDTO> toListDto(List<CasoEstudio> list);

    @AfterMapping
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
        casoEstudioDTO.setGrupos(mapGrupos(casoEstudio.getGrupos()));
    }

    default List<Long> mapRecursoMultimediaList(List<RecursoMultimedia> recursos) {
        return recursos.stream()
                .map(RecursoMultimedia::getId)
                .collect(Collectors.toList());
    }

    default List<Long> mapProfesores(List<Profesor> profesores) {
        return profesores.stream()
                .map(Profesor::getId)
                .collect(Collectors.toList());
    }

    default List<Long> mapGrupos(List<Grupo> grupos) {
        return grupos.stream()
                .map(Grupo::getId)
                .collect(Collectors.toList());
    }
/*
    @AfterMapping
    default void mapProfesoresAndGrupos(@MappingTarget CasoEstudio casoEstudio, CasoEstudioReq req) {
        casoEstudio.setProfesores(mapProfesores(req.getProfesores()));
        casoEstudio.setGrupos(mapGrupos(req.getGrupos()));
    }

    default List<Profesor> mapProfesores(List<Long> profesoresIds) {
        // Lógica para obtener la lista de profesores a partir de los IDs
        // Puedes utilizar un servicio, un repositorio, etc.
        // En este ejemplo, asumiremos que existe un método getById en un servicio de profesores
        return profesoresIds.stream()
                .map(profesorService::get)
                .collect(Collectors.toList());
    }

    default List<Grupo> mapGrupos(List<Long> gruposIds) {
        // Lógica para obtener la lista de grupos a partir de los IDs
        // Puedes utilizar un servicio, un repositorio, etc.
        // En este ejemplo, asumiremos que existe un método getById en un servicio de grupos
        return gruposIds.stream()
                .map(grupoService::getById)
                .collect(Collectors.toList());
    }*/

}
