package com.escom.Creadordecasos.Repository;

import com.escom.Creadordecasos.Entity.Grupo;
import com.escom.Creadordecasos.Entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrupoRepository  extends JpaRepository<Grupo, Long> {
    List<Grupo> findByProfesor(Profesor profesor);
}
