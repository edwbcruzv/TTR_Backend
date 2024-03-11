package com.escom.Creadordecasos.Repository;

import com.escom.Creadordecasos.Entity.Estudiante;
import com.escom.Creadordecasos.Entity.Grupo;
import com.escom.Creadordecasos.Entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository  extends JpaRepository<Inscripcion, Long> {

    List<Inscripcion> findByEstudiante(Estudiante estudiante);

    List<Inscripcion> findByGrupo(Grupo grupo);
    //boolean existsByEstudiante_IdAndGrupo_Id(Long id, Long id1);
}
