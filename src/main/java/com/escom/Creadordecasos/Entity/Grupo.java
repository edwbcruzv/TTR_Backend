package com.escom.Creadordecasos.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // genera setters y getters
@AllArgsConstructor // genera constructor con todos los atributos
@NoArgsConstructor // Constructor sin parametros
@Entity
public class Grupo {

    @Id// lo define como el  Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es como el autoincrement
    private Long id;

    @Column
    private String nombre;

    @Column
    private String estatus;

    @ManyToOne
    private Profesor profesor;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
    private List<Equipo> equipos;

    @OneToMany(mappedBy = "grupo")
    private List<Inscripcion> inscripciones;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "Grupo_CasoEstudio",
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "caso_estudio_id"))
    private List<CasoEstudio> casos_estudio;



}
