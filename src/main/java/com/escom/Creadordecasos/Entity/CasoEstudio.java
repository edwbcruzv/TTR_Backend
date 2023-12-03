package com.escom.Creadordecasos.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data // genera setters y getters
@AllArgsConstructor // genera constructor con todos los atributos
@NoArgsConstructor // Constructor sin parametros
@Entity
public class CasoEstudio {

    @Id// lo define como el  Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es como el autoincrement
    private Long Id;

    @Column
    private String introduccion;

    @Column
    private String resumen;

    @ManyToMany(mappedBy = "casos_estudio")
    private List<RecursoMultimedia> resumen_multimedia;

    private String objetivos;

    @ManyToMany(mappedBy = "casos_estudio")
    private List<RecursoMultimedia> objetivos_multimedia;

    private String clasificacion;

    @ManyToMany(mappedBy = "casos_estudio")
    private List<RecursoMultimedia> clasificacion_multimedia;

    private String lugar;

    @ManyToMany(mappedBy = "casos_estudio")
    private List<RecursoMultimedia> lugar_multimedia;

    private String temporalidad;

    @ManyToMany(mappedBy = "casos_estudio")
    private List<RecursoMultimedia> temporalidad_multimedia;

    private String protagonistas;

    @ManyToMany(mappedBy = "casos_estudio")
    private List<RecursoMultimedia> protagonistas_multimedia;

    private String organizacion;

    @ManyToMany(mappedBy = "casos_estudio")
    private List<RecursoMultimedia> organizacion_multimedia;

    private String preguntas;

    @ManyToMany(mappedBy = "casos_estudio")
    private List<RecursoMultimedia> preguntas_multimedia;

    private String riesgos;

    @ManyToMany(mappedBy = "casos_estudio")
    private List<RecursoMultimedia> riesgos_multimedia;

    private String resultados;

    @ManyToMany(mappedBy = "casos_estudio")
    private List<RecursoMultimedia> resultados_multimedia;

    private String anexos;

    @ManyToMany(mappedBy = "casos_estudio")
    private List<RecursoMultimedia> anexos_multimedia;

    @Column
    private String conclusion;

    @Column
    private String comentarios;

    @Temporal(TemporalType.DATE)
    private Date fecha_creacion;

    @Temporal(TemporalType.DATE)
    private Date fecha_vencimiento;

    @ManyToMany(mappedBy = "casos_estudio")
    private List<Profesor> profesores;

    @ManyToMany(mappedBy = "casos_estudio")
    private List<Grupo> grupos;

}
