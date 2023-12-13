package com.escom.Creadordecasos.Repository;

import com.escom.Creadordecasos.Entity.CasoEstudio;
import com.escom.Creadordecasos.Entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CasoEstudioRepository  extends JpaRepository<CasoEstudio, Long> {
    List<CasoEstudio> findByProfesores_Id(Long id);
    List<CasoEstudio> findByProfesor(Profesor profesor);
    //List<CasoEstudio> findAllByVisible(Boolean visible);

    List<CasoEstudio> findAllByClasificacion(String clasificacion);
}
