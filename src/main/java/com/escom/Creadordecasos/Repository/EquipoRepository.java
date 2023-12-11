package com.escom.Creadordecasos.Repository;

import com.escom.Creadordecasos.Entity.Equipo;
import com.escom.Creadordecasos.Entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository  extends JpaRepository<Equipo, Long> {
    List<Equipo> findByGrupo(Grupo grupo);
}
