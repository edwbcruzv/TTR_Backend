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
    private String Introduccion;

    @Column
    private String IntegracionInicial;

    @Column
    private String Comentarios;

    @Temporal(TemporalType.DATE)
    private Date FechaCreacion;

    @Temporal(TemporalType.DATE)
    private Date FechaVencimiento;

    @Column
    private String Preguntas;

    @Column
    private String Conclusion;

    @ManyToMany(mappedBy = "CasosEstudio")
    private List<RecursoMultimedia> RecursosMultimedia;

    @ManyToMany(mappedBy = "CasosEstudio")
    private List<Profesor> Profesores;

}
