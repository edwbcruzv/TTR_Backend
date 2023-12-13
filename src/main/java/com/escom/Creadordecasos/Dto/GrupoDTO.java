package com.escom.Creadordecasos.Dto;

import com.escom.Creadordecasos.Entity.Profesor;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GrupoDTO implements Serializable {
    private Long id;
    private String clave;
    private LocalDateTime fecha_vencimiento;
    private String nombre_grupo;
    private String nombre_materia;
    private Long profesor_id;
    private List<Long> equipos_ids;
    private List<Long> inscripciones_ids;
}
