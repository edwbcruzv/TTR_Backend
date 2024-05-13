package com.escom.CreadorPracticas.Service.Practica.Bodies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PracticaReq {
    private Long id;
    private Long profesorId;
    private String titulo;
    private String descripcion;
    private List<Long> recursosMultimedia;
    private String comentarios;
    private String rubrica;
}
