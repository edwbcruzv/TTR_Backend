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
    private String estudiante_nombre;
    private String grupo_nombre;
    private String profesor_nombre;
    private float calificacion;
    private String clave;
}
