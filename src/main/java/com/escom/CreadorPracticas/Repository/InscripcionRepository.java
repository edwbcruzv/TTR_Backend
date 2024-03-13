package com.escom.CreadorPracticas.Repository;

import com.escom.CreadorPracticas.Entity.Estudiante;
import com.escom.CreadorPracticas.Entity.Grupo;
import com.escom.CreadorPracticas.Entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository  extends JpaRepository<Inscripcion, Long> {

    List<Inscripcion> findByEstudiante(Estudiante estudiante);

    List<Inscripcion> findByGrupo(Grupo grupo);

    List<Estudiante> findAllEstudianteByGrupoId(Long grupoId);
}
