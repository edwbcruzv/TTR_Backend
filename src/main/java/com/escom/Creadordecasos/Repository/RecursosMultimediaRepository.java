package com.escom.Creadordecasos.Repository;

import com.escom.Creadordecasos.Entity.RecursoMultimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecursosMultimediaRepository  extends JpaRepository<RecursoMultimedia, Long>{
    List<RecursoMultimedia> findByIdIn(List<Long> list);
    @Query("SELECT e FROM RecursoMultimedia e WHERE e.id IN :ids")
    List<RecursoMultimedia> findMultimediasByIds(@Param("ids") List<Long> ids);

    @Query("SELECT rm FROM RecursoMultimedia rm WHERE rm.caso_estudio.id = :casoEstudioId")
    List<RecursoMultimedia> findByCasoEstudioId(@Param("casoEstudioId") Long casoEstudioId);

}