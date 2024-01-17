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
public class RecursoMultimedia {

    @Id// lo define como el  Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es como el autoincrement
    private Long id;

    @Column
    private String nombre;

    @Column
    private String path_src;

    @ManyToOne
    @JoinColumn(name = "caso_estudio_id")
    private CasoEstudio caso_estudio;

}
