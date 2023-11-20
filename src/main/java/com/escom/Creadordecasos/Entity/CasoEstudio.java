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
    private String integracionInicial;

    @Column
    private String comentarios;

    @Temporal(TemporalType.DATE)
    private Date fecha_creacion;

    @Temporal(TemporalType.DATE)
    private Date fecha_vencimiento;

    @Column
    private String preguntas;

    @Column
    private String conclusion;

    @ManyToMany(mappedBy = "casos_estudio")
    private List<RecursoMultimedia> recursos_multimedia;

    @ManyToMany(mappedBy = "casos_estudio")
    private List<Profesor> profesores;

    @ManyToMany(mappedBy = "casos_estudio")
    private List<Grupo> grupos;

}
