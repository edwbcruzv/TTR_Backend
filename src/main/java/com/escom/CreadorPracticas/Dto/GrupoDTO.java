package com.escom.CreadorPracticas.Dto;


import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GrupoDTO implements Serializable {
    private Long id;
    private String clave;
    private String nombre;
    private String codigo;
    private LocalDateTime fechaVencimientoCodigo;
    private String profesorNombre;
    private List<Long> equiposIds;
}
