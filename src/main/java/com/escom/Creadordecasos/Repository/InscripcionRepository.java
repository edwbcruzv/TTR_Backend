package com.escom.Creadordecasos.Repository;

import com.escom.Creadordecasos.Entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository  extends JpaRepository<Inscripcion, Long> {

    boolean existsByEstudiante_IdAndGrupo_Id(Long id, Long id1);
}
