package com.escom.Creadordecasos.Service.Equipos.Bodies;

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
    private String nombre;
    private Long grupo_id;
    private List<Long> estudiantes_ids;
    private List<Long> casos_estudio_ids;
    private String Solucion;
}
