package com.escom.Creadordecasos.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data // genera setters y getters
@AllArgsConstructor // genera constructor con todos los atributos
@NoArgsConstructor // Constructor sin parametros
@Entity
@Builder
public class Grupo {

    @Id// lo define como el  Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es como el autoincrement
    private Long id;

    @Column
    private String clave;

    @Column
    private LocalDateTime fecha_vencimiento;

    @Column
    private String nombre_grupo;

    @Column
    private String nombre_materia;

    @ManyToOne(targetEntity = Profesor.class, fetch = FetchType.LAZY)
    private Profesor profesor;

    @OneToMany(targetEntity = Equipo.class,fetch = FetchType.LAZY,mappedBy = "grupo")
    private List<Equipo> equipos;

    @OneToMany(targetEntity = Inscripcion.class,fetch = FetchType.LAZY,mappedBy = "grupo")
    private List<Inscripcion> inscripciones;
}
