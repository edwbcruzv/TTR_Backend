package com.escom.Creadordecasos.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long clave;

    @Column
    private String nombre_grupo;

    @Column
    private String nombre_materiay;

    @ManyToOne
    private Profesor profesor;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
    private List<Equipo> equipos;

    @OneToMany(mappedBy = "grupo")
    private List<Inscripcion> inscripciones;
}
