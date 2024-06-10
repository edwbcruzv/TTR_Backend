package com.escom.CreadorPracticas.Dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PracticaBitDTO {
    private Long practicaId;
    private String titulo;
    private boolean esIndividual;
    private List<Long> solucionesIds;
    private List<String> estudiantesNombres;
    private List<Long> equiposIds;
    private List<String> equiposNombres;
}
