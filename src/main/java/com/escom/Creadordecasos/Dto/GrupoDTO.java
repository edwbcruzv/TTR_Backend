package com.escom.Creadordecasos.Dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GrupoDTO implements Serializable {
    private Long id;
    private Long profesor_id;
    private int status;
    private String nombre;
}
