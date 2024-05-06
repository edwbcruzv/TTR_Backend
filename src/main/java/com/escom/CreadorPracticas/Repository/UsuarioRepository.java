package com.escom.CreadorPracticas.Repository;

import com.escom.CreadorPracticas.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Cuando regresamos un Optional decimos que podemos regresar null
    Optional<Usuario> findByUsername(String username);
    void deleteByUsername(String username);
    Optional<Usuario> findByUsernameIgnoreCase(String username);

    Optional<Usuario> findByEmail(String email);

    Usuario  getByUsername(String username);
    List<Usuario> findAllByRol(String rol);

    //List<Usuario> findByUsernameContaining(String str);

    //List<Usuario>  findByNombreContaining(String str);

    //@Query("SELECT e FROM Usuario e WHERE e.apellido_paterno LIKE %:apellidoPaterno%")
    //List<Usuario> findByApellidoPaternoContaining(@Param("apellidoPaterno") String apellidoPaterno);

    //List<Usuario>  findByApellidomaternoContaining(String str);
}