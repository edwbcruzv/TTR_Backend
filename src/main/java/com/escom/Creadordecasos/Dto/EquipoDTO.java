package com.escom.Creadordecasos.Dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.List;

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
