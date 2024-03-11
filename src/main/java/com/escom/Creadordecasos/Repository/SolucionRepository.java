package com.escom.Creadordecasos.Repository;

import com.escom.Creadordecasos.Entity.Solucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolucionRepository extends JpaRepository<Solucion, Long> {

}
