package com.escom.CreadorPracticas.Repository;

import com.escom.CreadorPracticas.Entity.Solucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolucionRepository extends JpaRepository<Solucion, Long> {

}
