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
    private Long id;

    @Column
    private String titulo;

    @Column
    private String descripcion;

    @Column
    private String src;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "RecursoMultimedia_CasoEstudio",
            joinColumns = @JoinColumn(name = "recurso_multimedia_id"),
            inverseJoinColumns = @JoinColumn(name = "caso_estudio_id"))
    private List<CasoEstudio> casos_estudio;

    private Integer numero_orden;

}
