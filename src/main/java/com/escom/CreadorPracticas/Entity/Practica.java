package com.escom.CreadorPracticas.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Practica {

    @Id// lo define como el  Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es como el autoincrement
    private Long id;

    @ManyToOne(targetEntity = Profesor.class, fetch = FetchType.LAZY)
    private Profesor profesor;

    @NotNull
    @Column(unique = true)
    private String titulo;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @NotNull
    @Column
    private LocalDateTime fechaCreacion;

    @OneToMany(targetEntity = Solucion.class,fetch = FetchType.EAGER,mappedBy = "practica")
    private List<Solucion> soluciones;

    @OneToMany(targetEntity = RecursoMultimedia.class,fetch = FetchType.EAGER,mappedBy = "practica")
    private List<RecursoMultimedia> recursosMultimedia;

    @Column(columnDefinition = "TEXT")
    private String comentarios;

    @Column(columnDefinition = "TEXT")
    private String rubrica;



}