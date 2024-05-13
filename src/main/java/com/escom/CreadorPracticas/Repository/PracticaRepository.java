package com.escom.CreadorPracticas.Repository;

import com.escom.CreadorPracticas.Entity.Practica;
import com.escom.CreadorPracticas.Entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracticaRepository extends JpaRepository<Practica, Long> {
    List<Practica> findByProfesor(Profesor profesor);
}
