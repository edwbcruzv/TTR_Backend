package com.escom.CreadorPracticas.Repository;

import com.escom.CreadorPracticas.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolucionRepository extends JpaRepository<Solucion, Long> {
    List<Solucion> findByIdIn(List<Long> list);
    List<Solucion> findByEquipo(Equipo equipo);
    List<Solucion> findByEstudiante(Estudiante estudiante);

    List<Solucion> findByPracticaInAndEquipoIn(List<Practica> practicas, List<Equipo> equipos);
    List<Solucion> findByPracticaInAndEstudianteIn(List<Practica> practicas, List<Estudiante> estudiantes);
}
