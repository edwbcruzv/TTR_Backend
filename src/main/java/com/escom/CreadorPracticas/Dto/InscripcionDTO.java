package com.escom.CreadorPracticas.Dto;


import lombok.*;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InscripcionDTO implements Serializable {
    private String estudianteUsername;
    private String estudianteNombre;
    private String profesorNombre;
    private String grupoId;
    private String grupoClave;
    private String grupoNombre;
    private float calificacion;
}
