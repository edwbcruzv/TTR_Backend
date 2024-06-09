package com.escom.CreadorPracticas.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data // genera setters y getters
@AllArgsConstructor // genera constructor con todos los atributos
@NoArgsConstructor // Constructor sin parametros
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "username")
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Profesor extends Usuario{

    @Column
    private String cedula;

    @OneToMany(targetEntity = Grupo.class,fetch = FetchType.EAGER,mappedBy = "profesor")
    private List<Grupo> grupos;

    @OneToMany(targetEntity = Practica.class,fetch = FetchType.LAZY,mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Practica> practicas;

}
