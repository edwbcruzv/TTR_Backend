package com.escom.Creadordecasos.Service.Equipos.Bodies;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EquipoReq {
    private Long id;
    @NotBlank
    private String nombre;
    @NotBlank
    private Long grupo_id;
    @NotBlank
    private List<Long> estudiantes_ids;
    private List<Long> casos_estudio_ids;
    private String Solucion;
}
