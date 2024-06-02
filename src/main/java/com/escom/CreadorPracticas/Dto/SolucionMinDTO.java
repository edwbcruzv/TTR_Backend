package com.escom.CreadorPracticas.Dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SolucionMinDTO {
    private Long id;
    private Long practicaId;
    private String practicaTitulo;
    private String practicaDescripcion;
    private String estudianteNombre;
    private String equipoNombre;
    private LocalDateTime fechaUltimaEdicion;
    private LocalDateTime fechaLimiteEntrega;
    private double calificacion;
}
