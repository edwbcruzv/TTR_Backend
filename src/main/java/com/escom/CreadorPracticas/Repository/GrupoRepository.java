package com.escom.CreadorPracticas.Repository;

import com.escom.CreadorPracticas.Entity.Grupo;
import com.escom.CreadorPracticas.Entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository  extends JpaRepository<Grupo, Long> {
    List<Grupo> findByProfesor(Profesor profesor);
}
