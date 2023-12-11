package com.escom.Creadordecasos.Repository;

import com.escom.Creadordecasos.Entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajesRepository  extends JpaRepository<Mensaje, Long> {

}
