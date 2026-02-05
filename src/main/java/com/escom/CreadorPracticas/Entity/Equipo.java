package com.escom.CreadorPracticas.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "equipos")
public class Equipo {

    @Id// lo define como el  Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es como el autoincrement
    private Long id;

    @NotNull
    @Column
    private String nombre;

    @ManyToOne(targetEntity = Grupo.class,fetch = FetchType.EAGER)
    private Grupo grupo;

    @ManyToMany(mappedBy = "equipos", cascade = CascadeType.PERSIST)
    private List<Estudiante> estudiantes;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.REMOVE)
    private List<Solucion> Soluciones;

}
