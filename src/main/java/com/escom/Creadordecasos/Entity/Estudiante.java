package com.escom.Creadordecasos.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data // genera setters y getters
@AllArgsConstructor // genera constructor con todos los atributos
@NoArgsConstructor // Constructor sin parametros
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Estudiante extends Usuario{

    @Column
    private String boleta;

    @Column
    private Integer semestre;

    @OneToMany(targetEntity = Inscripcion.class,fetch = FetchType.EAGER,mappedBy = "estudiante")
    private List<Inscripcion> inscripciones;

    @ManyToMany(targetEntity = Equipo.class,fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Equipo> equipos;

}
