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
@Table(name = "grupos")
public class Grupo {

    @Id// lo define como el  Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es como el autoincrement
    private Long id;

    @NotNull
    @Column
    private String clave;

    @NotNull
    @Column
    private String nombre;

    @NotNull
    @Column(unique = true)
    private String codigo;

    @NotNull
    @Column
    private LocalDateTime fechaVencimientoCodigo;

    @ManyToOne(targetEntity = Profesor.class, fetch = FetchType.LAZY)
    private Profesor profesor;

    @OneToMany(targetEntity = Equipo.class,fetch = FetchType.LAZY,mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipo> equipos;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscripcion> inscripciones;

}
