package com.escom.Creadordecasos.Repository.RecursosMultimedia;

import com.escom.Creadordecasos.Entity.CasoEstudio;
import com.escom.Creadordecasos.Entity.RecursoMultimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecursosMultimediaRepository  extends JpaRepository<RecursoMultimedia, Long>{
    List<RecursoMultimedia> findByIdIn(List<Long> list);
}