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
    private Long Id;

    @Column
    private String Nombre;

    @Column
    private String Estatus;

    @ManyToOne
    private Profesor Profesor;

    @OneToMany(mappedBy = "", cascade = CascadeType.ALL)
    private List<Equipo> Equipos;

    @OneToMany(mappedBy = "Grupo")
    private List<Inscripcion> Inscripciones;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "Grupo_CasoEstudio",
            joinColumns = @JoinColumn(name = "GrupoId"),
            inverseJoinColumns = @JoinColumn(name = "CasoEstudioId"))
    private List<CasoEstudio> CasosEstudio;



}
