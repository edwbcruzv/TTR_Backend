package com.escom.CreadorPracticas.Repository;

import com.escom.CreadorPracticas.Dto.PracticaBitDTO;
import com.escom.CreadorPracticas.Entity.Practica;
import com.escom.CreadorPracticas.Entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracticaRepository extends JpaRepository<Practica, Long> {
    List<Practica> findByProfesor(Profesor profesor);

    @Query("SELECT new com.escom.CreadorPracticas.Dto.PracticaBitDTO(" +
            "p.id, p.titulo, " +
            "CASE WHEN s.estudiante IS NOT NULL THEN TRUE ELSE FALSE END, " +
            "s.id, " +
            "CASE WHEN s.estudiante IS NOT NULL THEN s.estudiante.username ELSE NULL END, " +
            "CASE WHEN s.equipo IS NOT NULL THEN s.equipo.id ELSE NULL END, " +
            "CASE WHEN s.equipo IS NOT NULL THEN s.equipo.nombre ELSE NULL END) " +
            "FROM Practica p JOIN p.soluciones s " +
            "WHERE (s.estudiante IN (SELECT i.estudiante FROM Inscripcion i WHERE i.inscripcionKey.grupo_id = :grupoId) " +
            "OR s.equipo IN (SELECT e FROM Equipo e WHERE e.grupo.id = :grupoId))")
    List<PracticaBitDTO> findPracticasBitacoraByGrupoId(@Param("grupoId") Long grupoId);
}
