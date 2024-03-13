package com.escom.CreadorPracticas.Repository;

import com.escom.CreadorPracticas.Entity.Practica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticaRepository extends JpaRepository<Practica, Long> {

}
