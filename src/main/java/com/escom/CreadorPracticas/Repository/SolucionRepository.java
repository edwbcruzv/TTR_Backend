package com.escom.CreadorPracticas.Repository;

import com.escom.CreadorPracticas.Entity.Equipo;
import com.escom.CreadorPracticas.Entity.Solucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolucionRepository extends JpaRepository<Solucion, Long> {
    List<Solucion> findByIdIn(List<Long> list);
    List<Solucion> findByEquipo(Equipo equipo);
}
