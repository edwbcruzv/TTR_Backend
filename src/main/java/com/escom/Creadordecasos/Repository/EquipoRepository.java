package com.escom.Creadordecasos.Repository;

import com.escom.Creadordecasos.Entity.Equipo;
import com.escom.Creadordecasos.Entity.Estudiante;
import com.escom.Creadordecasos.Entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EquipoRepository  extends JpaRepository<Equipo, Long> {
    List<Equipo> findByGrupo(Grupo grupo);
    List<Equipo> findByIdIn(List<Long> list);
/*
    @Transactional
    @Modifying
    @Query("update Equipo e set e.Solucion = ?1 where e.id = ?2")
    int updateSolucionById(String Solucion, Long id);
    */
}
