package com.escom.Creadordecasos.Dto;


import com.escom.Creadordecasos.Entity.Practica;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
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
    private Practica practica;
}
