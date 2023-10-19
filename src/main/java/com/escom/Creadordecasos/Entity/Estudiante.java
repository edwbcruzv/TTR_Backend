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
@PrimaryKeyJoinColumn(referencedColumnName = "Id")
public class Estudiante extends Usuario{

    @Column
    private String Boleta;

    @Column
    private Integer Semestre;

    @OneToMany(mappedBy = "Estudiante")
    private List<Inscripcion> Inscripciones;

    @ManyToMany(mappedBy = "Estudiantes")
    private List<Equipo> Equipos;

}
