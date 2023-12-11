package com.escom.Creadordecasos.Service.Inscripciones.Bodies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InscripcionReq {
    private Long id;
    private Long estudiante_id;
    private Long grupo_id;
    private Float calificacion;
}
