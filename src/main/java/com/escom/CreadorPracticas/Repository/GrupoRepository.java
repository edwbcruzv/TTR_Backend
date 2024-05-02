package com.escom.CreadorPracticas.Repository;

import com.escom.CreadorPracticas.Entity.Grupo;
import com.escom.CreadorPracticas.Entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrupoRepository  extends JpaRepository<Grupo, Long> {
    List<Grupo> findByProfesor(Profesor profesor);


    Optional<Grupo> findByCodigo(String codigo);
}
