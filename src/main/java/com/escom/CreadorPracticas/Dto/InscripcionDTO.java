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
    private String grupoId;
    private float calificacion;
}
