package com.escom.Creadordecasos.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data // genera setters y getters
@AllArgsConstructor // genera constructor con todos los atributos
@NoArgsConstructor // Constructor sin parametros
@Entity
@Builder
public class CasoEstudio {

    @Id// lo define como el  Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es como el autoincrement
    private Long id;
    @ManyToOne(targetEntity = Profesor.class, fetch = FetchType.LAZY)
    private Profesor profesor;
    @Column
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String introduccion;

    @Column(columnDefinition = "TEXT")
    private String resumen;

    @ElementCollection
    @CollectionTable(name = "resumen_multimedia", joinColumns = @JoinColumn(name = "caso_estudio_id"))
    private List<RecursoMultimedia> resumen_multimedia_list;

    @Column(columnDefinition = "TEXT")
    private String objetivos;

    @ElementCollection
    @CollectionTable(name = "objetivos_multimedia", joinColumns = @JoinColumn(name = "caso_estudio_id"))
    private List<RecursoMultimedia> objetivos_multimedia_list;

    @Column(columnDefinition = "TEXT")
    private String clasificacion;

    @ElementCollection
    @CollectionTable(name = "clasificacion_multimedia", joinColumns = @JoinColumn(name = "caso_estudio_id"))
    private List<RecursoMultimedia> clasificacion_multimedia_list;

    @Column(columnDefinition = "TEXT")
    private String lugar;

    @ElementCollection
    @CollectionTable(name = "lugar_multimedia", joinColumns = @JoinColumn(name = "caso_estudio_id"))
    private List<RecursoMultimedia> lugar_multimedia_list;

    @Column(columnDefinition = "TEXT")
    private String temporalidades;

    @ElementCollection
    @CollectionTable(name = "temporalidades_multimedia", joinColumns = @JoinColumn(name = "caso_estudio_id"))
    private List<RecursoMultimedia> temporalidades_multimedia_list;

    @Column(columnDefinition = "TEXT")
    private String protagonistas;

    @ElementCollection
    @CollectionTable(name = "protagonistas_multimedia", joinColumns = @JoinColumn(name = "caso_estudio_id"))
    private List<RecursoMultimedia> protagonistas_multimedia_list;

    @Column(columnDefinition = "TEXT")
    private String organizaciones;

    @ElementCollection
    @CollectionTable(name = "organizaciones_multimedia", joinColumns = @JoinColumn(name = "caso_estudio_id"))
    private List<RecursoMultimedia> organizaciones_multimedia_list;

    @Column(columnDefinition = "TEXT")
    private String preguntas;

    @ElementCollection
    @CollectionTable(name = "preguntas_multimedia", joinColumns = @JoinColumn(name = "caso_estudio_id"))
    private List<RecursoMultimedia> preguntas_multimedia_list;

    @Column(columnDefinition = "TEXT")
    private String riesgos;

    @ElementCollection
    @CollectionTable(name = "riesgos_multimedia", joinColumns = @JoinColumn(name = "caso_estudio_id"))
    private List<RecursoMultimedia> riesgos_multimedia_list;

    @Column(columnDefinition = "TEXT")
    private String resultados;

    @ElementCollection
    @CollectionTable(name = "resultados_multimedia", joinColumns = @JoinColumn(name = "caso_estudio_id"))
    private List<RecursoMultimedia> resultados_multimedia_list;

    @Column(columnDefinition = "TEXT")
    private String anexos;

    @ElementCollection
    @CollectionTable(name = "anexos_multimedia", joinColumns = @JoinColumn(name = "caso_estudio_id"))
    private List<RecursoMultimedia> anexos_multimedia_list;

    @Column(columnDefinition = "TEXT")
    private String conclusion;

    @Column(columnDefinition = "TEXT")
    private String comentarios;

    @Column
    private Date fecha_creacion;

    @Temporal(TemporalType.DATE)
    private Date fecha_vencimiento;

    @ManyToMany(mappedBy = "casos_estudio_compartidos")
    private List<Profesor> profesores;

    @ManyToMany(mappedBy = "casos_estudio")
    private List<Equipo> equipos;

}