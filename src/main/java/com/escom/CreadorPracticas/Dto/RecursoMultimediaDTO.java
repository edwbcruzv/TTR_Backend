package com.escom.CreadorPracticas.Dto;


import com.escom.CreadorPracticas.Entity.Practica;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RecursoMultimediaDTO {
    private Long id;
    private String nombre;
    private String srcFile;
    private Long practicaId;
}
