package com.escom.CreadorPracticas.Service.Equipo.Bodies;

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
    private Long grupoId;
    private List<String> estudiantesUsernames;
    private List<Long> solucionesIds;
}
