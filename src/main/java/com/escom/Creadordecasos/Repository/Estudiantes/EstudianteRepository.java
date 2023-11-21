package com.escom.Creadordecasos.Repository.Estudiantes;

import com.escom.Creadordecasos.Entity.Estudiante;
import com.escom.Creadordecasos.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository  extends JpaRepository<Estudiante, Long> {
    Optional<Estudiante> findByUsername(String username);

    Optional<Estudiante> findByUsernameIgnoreCase(String username);

    Optional<Estudiante> findByEmail(String email);

    Estudiante  getByUsername(String username);

    List<Estudiante> findByUsernameContaining(String str);

    List<Estudiante>  findByNombreContaining(String str);
}
