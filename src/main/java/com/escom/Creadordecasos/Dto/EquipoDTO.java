package com.escom.Creadordecasos.Dto;

import com.escom.Creadordecasos.Entity.CasoEstudio;
import com.escom.Creadordecasos.Entity.Estudiante;
import com.escom.Creadordecasos.Entity.Grupo;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
    private Long grupo_id;
    private List<Long> estudiantes_ids;
    private List<Long> casos_estudio_ids;
    private String Solucion;
}
