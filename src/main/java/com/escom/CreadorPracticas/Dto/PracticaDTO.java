package com.escom.CreadorPracticas.Dto;


import com.escom.CreadorPracticas.Entity.Solucion;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PracticaDTO {
    private Long id;
    private String nombreprofesor;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private List<Long> recursosMultimedia;
    private List<Long> soluciones;
    private String comentarios;
    private String rubrica;

}
