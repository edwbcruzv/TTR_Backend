package com.escom.Creadordecasos.Repository.Inscripciones;

import com.escom.Creadordecasos.Entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionesRepository  extends JpaRepository<Inscripcion, Long> {

}
