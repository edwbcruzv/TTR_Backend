package com.escom.CreadorPracticas.Dto;


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
    private Long grupoId;
    private List<String> estudiantesUsernames;
    private List<Long> solucionesIds;
}
