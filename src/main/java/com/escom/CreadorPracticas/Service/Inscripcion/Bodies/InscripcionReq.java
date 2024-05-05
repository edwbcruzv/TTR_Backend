package com.escom.CreadorPracticas.Service.Inscripcion.Bodies;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InscripcionReq {
    @NotBlank
    private Long grupoId;
    @NotBlank
    private String estudianteUsername;
    @NotBlank
    private Float calificacion;
    private String codigo;
}
