package com.escom.CreadorPracticas.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data // genera setters y getters
@AllArgsConstructor // genera constructor con todos los atributos
@NoArgsConstructor // Constructor sin parametros
@Entity
@Builder
public class Solucion {

    @Id// lo define como el  Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es como el autoincrement
    private Long id;

    @ManyToOne(targetEntity = Practica.class)
    private Practica practica;

    @Column(columnDefinition = "TEXT")
    private String strHtml;

    @Column(columnDefinition = "TEXT")
    private String strCss;

    @Column(columnDefinition = "TEXT")
    private String comentarios;

    @ManyToOne
    private Estudiante estudiante;

    @ManyToOne
    private Equipo equipo;

    @Column
    private LocalDateTime fechaUltimaEdicion;

    @Column
    private LocalDateTime fechaLimiteEntrega;

    @Column(columnDefinition = "TEXT")
    private String rubricaCalificada;




}
