package com.escom.Creadordecasos.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // genera setters y getters
@AllArgsConstructor // genera constructor con todos los atributos
@NoArgsConstructor // Constructor sin parametros
@Entity
@Builder
public class Inscripcion {

    @Id// lo define como el  Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es como el autoincrement
    private Long id;

    @ManyToOne(targetEntity = Estudiante.class,fetch = FetchType.EAGER)
    private Estudiante estudiante;

    @ManyToOne(targetEntity = Grupo.class,fetch = FetchType.EAGER)
    private Grupo grupo;

    @Column
    private float calificacion;

}
