package com.escom.Creadordecasos.Repository;

import com.escom.Creadordecasos.Entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProfesorRepository  extends JpaRepository<Profesor, Long> {
    Optional<Profesor> findByUsername(String username);
    List<Profesor> findByIdIn(List<Long> list);

    Optional<Profesor> findByUsernameIgnoreCase(String username);

    Optional<Profesor> findByEmail(String email);

    Profesor  getByUsername(String username);

    List<Profesor> findByUsernameContaining(String str);

    List<Profesor>  findByNombreContaining(String str);
}