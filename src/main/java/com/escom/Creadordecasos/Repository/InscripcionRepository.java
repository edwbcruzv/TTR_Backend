package com.escom.Creadordecasos.Repository;

import com.escom.Creadordecasos.Entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository  extends JpaRepository<Inscripcion, Long> {

}
