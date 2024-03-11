package com.escom.Creadordecasos.Service.Usuarios;

import com.escom.Creadordecasos.Dto.UsuarioDTO;
import com.escom.Creadordecasos.Entity.Usuario;
import com.escom.Creadordecasos.Mapper.UsuarioMapper;
import com.escom.Creadordecasos.Repository.UsuarioRepository;
import com.escom.Creadordecasos.Service.Usuarios.Bodies.UpdateUsuarioRequest;
import com.escom.Creadordecasos.Util.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private final UsuarioMapper usuarioMapper;
    public ResponseEntity<List<UsuarioDTO>> getAll(){

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //User userAuthenticated = (User) authentication.getPrincipal();
        //List<Usuario> usuarioList = usuarioRepository.findAll();
        List<Usuario> usuarioList = usuarioRepository.findAllByRol(Rol.ADMIN.toString());
        List<UsuarioDTO> list_dto = usuarioMapper.toListDto(usuarioList);
        return ResponseEntity.ok(list_dto);
    }

    public ResponseEntity<UsuarioDTO> get(Long id){
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()){
            return ResponseEntity.ok(usuarioMapper.toDto(optionalUsuario.get()));
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


    public ResponseEntity<Boolean> delete(Long id){
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()){
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

}
