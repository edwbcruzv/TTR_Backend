package com.escom.Creadordecasos.Dto;

import com.escom.Creadordecasos.Entity.Grupo;
import com.escom.Creadordecasos.Entity.Profesor;
import com.escom.Creadordecasos.Entity.RecursoMultimedia;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CasoEstudioDTO {
    private Long id;
    private Long profesor_id;
    private String titulo;
    private String introduccion;
    private String resumen;
    private List<Long> resumen_multimedia_list;
    private String objetivos;
    private List<Long> objetivos_multimedia_list;
    private String clasificacion;
    private List<Long> clasificacion_multimedia_list;
    private String lugar;
    private List<Long> lugar_multimedia_list;
    private String temporalidades;
    private List<Long> temporalidades_multimedia_list;
    private String protagonistas;
    private List<Long> protagonistas_multimedia_list;
    private String organizaciones;
    private List<Long> organizaciones_multimedia_list;
    private String preguntas;
    private List<Long> preguntas_multimedia_list;
    private String riesgos;
    private List<Long> riesgos_multimedia_list;
    private String resultados;
    private List<Long> resultados_multimedia_list;
    private String anexos;
    private List<Long> anexos_multimedia_list;
    private String conclusion;
    private String comentarios;
    private Date fecha_creacion;
    private Date fecha_vencimiento;
    private List<Long> profesores;
    private List<Long> equipos;
}
