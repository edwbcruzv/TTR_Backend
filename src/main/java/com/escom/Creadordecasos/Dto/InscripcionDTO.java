package com.escom.Creadordecasos.Dto;


import lombok.*;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InscripcionDTO implements Serializable {
    private String estudiante_username;
    private String grupo_clave;
    private String grupo_nombre;
}
