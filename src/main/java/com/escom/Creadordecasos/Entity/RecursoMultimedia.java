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
public class RecursoMultimedia {

    @Id// lo define como el  Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es como el autoincrement
    private Long Id;

    @Column
    private String Titulo;

    @Column
    private String Descripcion;

    @Column
    private String Src;

    @ManyToMany(mappedBy = "RecursosMultimedia")
    private List<CasoEstudio> CasosEstudio;

}
