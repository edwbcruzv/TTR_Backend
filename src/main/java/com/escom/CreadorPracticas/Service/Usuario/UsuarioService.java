package com.escom.CreadorPracticas.Service.Usuario;

import com.escom.CreadorPracticas.Entity.Usuario;
import com.escom.CreadorPracticas.Repository.UsuarioRepository;
import com.escom.CreadorPracticas.Service.Usuario.Bodies.UpdateUsuarioRequest;
import com.escom.CreadorPracticas.Util.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para los usuarios
 */
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    public ResponseEntity<List<Usuario>> getAll(){
        List<Usuario> usuarioList = usuarioRepository.findAllByRol(Rol.ADMIN.toString());
        return ResponseEntity.ok(usuarioList);
    }

    public ResponseEntity<Usuario> get(String username){
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(username);

        if (optionalUsuario.isPresent()){
            return ResponseEntity.ok(optionalUsuario.get());
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }
    public ResponseEntity<Boolean> update(UpdateUsuarioRequest updateUsuarioRequest){
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(updateUsuarioRequest.getUsername());

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();


            usuario.setUsername(updateUsuarioRequest.getUsername());
            usuario.setEmail(updateUsuarioRequest.getEmail());
            usuario.setNombre(updateUsuarioRequest.getNombre());
            usuario.setApellidoPaterno(updateUsuarioRequest.getApellidoPaterno());
            usuario.setApellidoMaterno(updateUsuarioRequest.getApellidoMaterno());
            usuario.setFechaNacimiento(updateUsuarioRequest.getFechaNacimiento());

            if (updateUsuarioRequest.getPassword() != null && !updateUsuarioRequest.getPassword().isEmpty())
                usuario.setPasswordHash(passwordEncoder.encode(updateUsuarioRequest.getPassword()));

            usuarioRepository.save(usuario);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Transactional
    public ResponseEntity<Boolean> delete(String username){
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(username);

        if (optionalUsuario.isPresent()){
            usuarioRepository.deleteByUsername(username);
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

}
