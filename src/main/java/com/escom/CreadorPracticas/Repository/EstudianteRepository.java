package com.escom.CreadorPracticas.Repository;

import com.escom.CreadorPracticas.Entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EstudianteRepository  extends JpaRepository<Estudiante, Long> {
    Optional<Estudiante> findByUsername(String username);

    void deleteByUsername(String username);

    @Query("SELECT e FROM Estudiante e WHERE e.username IN :usernames")
    List<Estudiante> findEstudiantesByUsernames(@Param("usernames") List<String> usernames);


    Optional<Estudiante> findByUsernameIgnoreCase(String username);

    Optional<Estudiante> findByEmail(String email);

    Estudiante  getByUsername(String username);

    List<Estudiante> findByUsernameContaining(String str);

    List<Estudiante>  findByNombreContaining(String str);

}
