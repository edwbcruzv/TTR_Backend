package com.escom.Creadordecasos.Repository.Usuarios;

import com.escom.Creadordecasos.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Cuando regresamos un Optional decimos que podemos regresar null
    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByEmail(String email);

    Usuario getByUsername(String username);


}