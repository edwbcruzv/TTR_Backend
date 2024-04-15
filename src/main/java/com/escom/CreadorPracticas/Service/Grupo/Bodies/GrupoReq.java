package com.escom.CreadorPracticas.Service.Grupo.Bodies;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GrupoReq {
    private Long id;
    @NotBlank
    private String clave;
    @NotBlank
    private String nombre;
    @NotBlank
    private String profesorUsername;
}
