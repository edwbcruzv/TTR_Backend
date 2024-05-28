package com.escom.CreadorPracticas.Service.Practica.Bodies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PracticaAsignarReq {
    private int option;
    private Long practicaId;
    private Long grupoId;
}
