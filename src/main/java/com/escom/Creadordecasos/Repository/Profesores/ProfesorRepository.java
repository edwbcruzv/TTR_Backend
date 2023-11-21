package com.escom.Creadordecasos.Repository.Profesores;

import com.escom.Creadordecasos.Entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfesorRepository  extends JpaRepository<Profesor, Long> {
    Optional<Profesor> findByUsername(String username);

    Optional<Profesor> findByUsernameIgnoreCase(String username);

    Optional<Profesor> findByEmail(String email);

    Profesor  getByUsername(String username);

    List<Profesor> findByUsernameContaining(String str);

    List<Profesor>  findByNombreContaining(String str);
}