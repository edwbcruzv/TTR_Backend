package com.escom.Creadordecasos.Dto;

import com.escom.Creadordecasos.Entity.Profesor;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GrupoDTO {
    private Long id;
    private Long clave;
    private String nombre_grupo;
    private String nombre_materia;
    private Long profesor;
    private List<Long> equipos;
    private List<Long> inscripciones;
}
