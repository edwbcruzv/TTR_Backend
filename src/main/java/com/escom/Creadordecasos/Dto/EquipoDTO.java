package com.escom.Creadordecasos.Dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EquipoDTO implements Serializable {
    private Long id;
    private Long grupo_id;
}
