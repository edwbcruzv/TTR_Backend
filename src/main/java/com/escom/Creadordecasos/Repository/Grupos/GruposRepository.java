package com.escom.Creadordecasos.Repository.Grupos;

import com.escom.Creadordecasos.Entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GruposRepository  extends JpaRepository<Grupo, Long> {

}
