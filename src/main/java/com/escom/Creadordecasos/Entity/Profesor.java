package com.escom.Creadordecasos.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data // genera setters y getters
@AllArgsConstructor // genera constructor con todos los atributos
@NoArgsConstructor // Constructor sin parametros
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Profesor extends Usuario{

    @Column
    private String cedula;

    @Column
    private String escuela;

    @OneToMany(targetEntity = Grupo.class,fetch = FetchType.EAGER,mappedBy = "profesor")
    private List<Grupo> grupos;

    @OneToMany(targetEntity = CasoEstudio.class,fetch = FetchType.LAZY,mappedBy = "profesor")
    private List<CasoEstudio> casos_estudio;

    @ManyToMany(targetEntity = CasoEstudio.class,fetch = FetchType.LAZY)
    private List<CasoEstudio> casos_estudio_compartidos;

}
