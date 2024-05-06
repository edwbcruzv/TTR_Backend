package com.escom.CreadorPracticas.Dto;


import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;


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
    private String profesorNombre = "No olvidar el nombre enel DTO";
}
