package com.escom.CreadorPracticas.Service.Solucion.Bodies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SolucionReq {
    private Long id;
    private Long practicaId;
    private String strHtml;
    private String strCss;
    private String comentarios;
    private String estudianteUsername;
    private Long equipoId;
    private LocalDateTime fechaLimiteEntrega;
    private String rubricaCalificada;
}
