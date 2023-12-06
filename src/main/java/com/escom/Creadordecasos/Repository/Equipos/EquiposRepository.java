package com.escom.Creadordecasos.Repository.Equipos;

import com.escom.Creadordecasos.Entity.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquiposRepository  extends JpaRepository<Equipo, Long> {

}
