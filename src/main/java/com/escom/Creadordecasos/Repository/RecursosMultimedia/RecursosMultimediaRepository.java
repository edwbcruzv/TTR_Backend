package com.escom.Creadordecasos.Repository.RecursosMultimedia;

import com.escom.Creadordecasos.Entity.RecursoMultimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursosMultimediaRepository  extends JpaRepository<RecursoMultimedia, Long>{

}