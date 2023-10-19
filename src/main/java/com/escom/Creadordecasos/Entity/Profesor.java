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
public class Profesor extends Usuario{

    @Column
    private String Cedula;

    @Column
    private String Escuela;

    @OneToMany(mappedBy = "Profesor")
    private List<Grupo> Grupos;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "Profesor_CasoEstudio",
            joinColumns = @JoinColumn(name = "ProfesorId"),
            inverseJoinColumns = @JoinColumn(name = "CasoEstudioId"))
    private List<CasoEstudio> CasosEstudio;
}
