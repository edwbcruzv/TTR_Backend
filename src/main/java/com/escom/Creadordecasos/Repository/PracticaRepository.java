package com.escom.Creadordecasos.Repository;

import com.escom.Creadordecasos.Entity.Practica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticaRepository extends JpaRepository<Practica, Long> {

}
