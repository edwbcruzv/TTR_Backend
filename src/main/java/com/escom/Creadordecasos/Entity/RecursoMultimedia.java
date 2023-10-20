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

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "RecursoMultimedia_CasoEstudio",
            joinColumns = @JoinColumn(name = "recurso_multimedia_id"),
            inverseJoinColumns = @JoinColumn(name = "caso_estudio_id"))
    private List<CasoEstudio> casos_estudio;

}
