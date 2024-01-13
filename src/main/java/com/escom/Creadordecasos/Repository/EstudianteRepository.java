package com.escom.Creadordecasos.Repository;

import com.escom.Creadordecasos.Entity.Estudiante;
import com.escom.Creadordecasos.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EstudianteRepository  extends JpaRepository<Estudiante, Long> {
    Optional<Estudiante> findByUsername(String username);

    @Query("SELECT e FROM Estudiante e WHERE e.id IN :ids")
    List<Estudiante> findEstudiantesByIds(@Param("ids") List<Long> ids);


    Optional<Estudiante> findByUsernameIgnoreCase(String username);

    Optional<Estudiante> findByEmail(String email);

    Estudiante  getByUsername(String username);

    List<Estudiante> findByUsernameContaining(String str);

    List<Estudiante>  findByNombreContaining(String str);

    // Nuevo método para obtener estudiantes por grupo
    @Query("SELECT e FROM Estudiante e JOIN e.inscripciones i WHERE i.grupo.id = :grupoId")
    List<Estudiante> findByGrupoId(@Param("grupoId") Long grupoId);

    // Nueva consulta para obtener estudiantes de un grupo sin equipo
    @Query("SELECT e FROM Estudiante e " +
            "JOIN e.inscripciones i " +
            "LEFT JOIN e.equipos eq " +
            "WHERE i.grupo.id = :grupoId AND (eq IS NULL OR SIZE(eq) = 0)")
    List<Estudiante> findEstudiantesByGrupoWithoutEquipo(@Param("grupoId") Long grupoId);
}
