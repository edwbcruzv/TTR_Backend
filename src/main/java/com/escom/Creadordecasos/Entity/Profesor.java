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

    @OneToMany(mappedBy = "profesor")
    private List<Grupo> grupos;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "Profesor_CasoEstudio",
            joinColumns = @JoinColumn(name = "profesor_id"),
            inverseJoinColumns = @JoinColumn(name = "caso_estudio_id"))
    private List<CasoEstudio> casos_estudio;


}
