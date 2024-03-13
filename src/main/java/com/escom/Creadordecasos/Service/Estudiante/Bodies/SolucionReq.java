package com.escom.Creadordecasos.Service.Estudiante.Bodies;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SolucionReq {
    private Long idEquipo;
    private Long uid;
    private String solucion;
}
