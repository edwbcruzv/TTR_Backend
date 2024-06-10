package com.escom.CreadorPracticas.Repository;

import com.escom.CreadorPracticas.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolucionRepository extends JpaRepository<Solucion, Long> {

    List<Solucion> findByIdIn(List<Long> list);
    List<Solucion> findByEquipo(Equipo equipo);
    List<Solucion> findByEstudiante(Estudiante estudiante);

    List<Solucion> findByEstudianteIsNull(); // Para soluciones en equipo
    List<Solucion> findByEquipoIsNull(); // Para soluciones individuales
    List<Solucion> findByEquipo_Id(Long equipoId); // Para soluciones de un equipo específico // ya esta
    List<Solucion> findByEstudianteIsNullAndEquipo_Id(Long equipoId); // Para soluciones individuales de un equipo específico
    @Query("SELECT s FROM Solucion s WHERE s.estudiante.username IN (SELECT i.inscripcionKey.estudiante_username FROM Inscripcion i WHERE i.inscripcionKey.grupo_id = :grupoId) AND s.equipo IS NULL")
    List<Solucion> findIndividualesByGrupoId(@Param("grupoId") Long grupoId); // Para soluciones individuales de un grupo específico

    List<Solucion> findByPracticaInAndEquipoIn(List<Practica> practicas, List<Equipo> equipos);
    List<Solucion> findByPracticaInAndEstudianteIn(List<Practica> practicas, List<Estudiante> estudiantes);
}
