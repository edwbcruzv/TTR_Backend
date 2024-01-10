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
public class Equipo {

    @Id// lo define como el  Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es como el autoincrement
    private Long id;
    @Column
    private String nombre;

    @ManyToOne(targetEntity = Grupo.class, fetch = FetchType.LAZY)
    private Grupo grupo;

    @ManyToMany(mappedBy = "equipos")
    private List<Estudiante> estudiantes;

    @ManyToMany(targetEntity = CasoEstudio.class,fetch = FetchType.LAZY)
    private List<CasoEstudio> casos_estudio;

    @Column
    private String Solucion;
}
