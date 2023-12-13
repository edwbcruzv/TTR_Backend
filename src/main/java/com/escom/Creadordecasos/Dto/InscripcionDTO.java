package com.escom.Creadordecasos.Dto;

import com.escom.Creadordecasos.Entity.Estudiante;
import com.escom.Creadordecasos.Entity.Grupo;
import lombok.*;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InscripcionDTO implements Serializable {
    private Long id;
    private Long estudiante_id;
    private Long grupo_id;
    private Float calificacion;
}
