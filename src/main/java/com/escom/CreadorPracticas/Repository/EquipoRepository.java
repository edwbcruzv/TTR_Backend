package com.escom.CreadorPracticas.Repository;

import com.escom.CreadorPracticas.Entity.Equipo;
import com.escom.CreadorPracticas.Entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository  extends JpaRepository<Equipo, Long> {
    List<Equipo> findByGrupo(Grupo grupo);
    List<Equipo> findByIdIn(List<Long> list);

}
