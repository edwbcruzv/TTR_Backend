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
public class Equipo {

    @Id// lo define como el  Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es como el autoincrement
    private Long Id;

    @ManyToOne
    private Grupo Grupo;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "Equipo_Estudiante",
    joinColumns = @JoinColumn(name = "EquipoId"),
    inverseJoinColumns = @JoinColumn(name = "EstudianteId"))
    private List<Estudiante> Estudiantes;

}
