package com.escom.Creadordecasos.Service.Grupo.Bodies;

import com.escom.Creadordecasos.Entity.Equipo;
import com.escom.Creadordecasos.Entity.Inscripcion;
import com.escom.Creadordecasos.Entity.Profesor;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GrupoReq {
    private Long id;
    private String nombre_grupo;
    private String nombre_materia;
    private Long profesor_id;
    private List<Long> equipos_ids;
    private List<Long> inscripciones_ids;
}
