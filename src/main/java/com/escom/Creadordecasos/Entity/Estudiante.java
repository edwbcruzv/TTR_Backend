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
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Estudiante extends Usuario{

    @Column
    private String boleta;

    @Column
    private Integer semestre;

    @OneToMany(mappedBy = "estudiante")
    private List<Inscripcion> inscripciones;

    @ManyToMany(mappedBy = "estudiantes")
    private List<Equipo> equipos;

}