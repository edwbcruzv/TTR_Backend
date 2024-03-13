package com.escom.CreadorPracticas.Dto;


import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EquipoDTO implements Serializable {
    private Long id;
    private String nombre;
    private String grupo_clave;
    private String grupo_nombre;
}
