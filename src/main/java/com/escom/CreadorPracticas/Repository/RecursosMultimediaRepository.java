package com.escom.CreadorPracticas.Repository;

import com.escom.CreadorPracticas.Entity.RecursoMultimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecursosMultimediaRepository  extends JpaRepository<RecursoMultimedia, Long>{
    List<RecursoMultimedia> findByIdIn(List<Long> list);

}