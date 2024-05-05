package com.escom.CreadorPracticas.Dto;

import com.escom.CreadorPracticas.Entity.Equipo;
import com.escom.CreadorPracticas.Entity.Estudiante;
import com.escom.CreadorPracticas.Entity.Practica;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SolucionDTO {
    private Long id;
    private Long practicaId;
    private String strHtml;
    private String strCss;
    private String comentarios;
    private String estudianteUsername;
    private Long equipoId;
    private LocalDateTime fechaUltimaEdicion;
    private LocalDateTime fechaLimiteEntrega;
    private LocalDateTime fechaEntrega;
    private String rubricaCalificada;
}
