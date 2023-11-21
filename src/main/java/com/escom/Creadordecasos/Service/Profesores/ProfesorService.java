package com.escom.Creadordecasos.Service.Profesores;

import com.escom.Creadordecasos.Dto.ProfesorDTO;
import com.escom.Creadordecasos.Entity.Profesor;
import com.escom.Creadordecasos.Mapper.ProfesorMapper;
import com.escom.Creadordecasos.Repository.Profesores.ProfesorRepository;
import com.escom.Creadordecasos.Service.Profesores.Bodies.UpdateProfesorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProfesorService {
    private final PasswordEncoder passwordEncoder;
    private final ProfesorRepository profesorRepository;
    private final ProfesorMapper profesorMapper;
    public ResponseEntity<List<ProfesorDTO>> getAll(){

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //User userAuthenticated = (User) authentication.getPrincipal();
        List<Profesor> usuarioList = profesorRepository.findAll();
        List<ProfesorDTO> list_dto = profesorMapper.toListDto(usuarioList);
        return ResponseEntity.ok(list_dto);
    }

    public ResponseEntity<ProfesorDTO> get(Long id){
        Optional<Profesor> optionalUsuario = profesorRepository.findById(id);

        if (optionalUsuario.isPresent()){
            return ResponseEntity.ok(profesorMapper.toDto(optionalUsuario.get()));
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }
    public ResponseEntity<Boolean> update(UpdateProfesorRequest updateProfesorRequest){
        Optional<Profesor> optionalUsuario = profesorRepository.findById(updateProfesorRequest.getId());

        if (optionalUsuario.isPresent()) {
            Profesor usuario = optionalUsuario.get();


            usuario.setUsername(updateProfesorRequest.getUsername());
            usuario.setEmail(updateProfesorRequest.getEmail());
            usuario.setNombre(updateProfesorRequest.getNombre());
            usuario.setApellido_paterno(updateProfesorRequest.getApellido_paterno());
            usuario.setApellido_materno(updateProfesorRequest.getApellido_materno());
            usuario.setFecha_nacimiento(updateProfesorRequest.getFecha_nacimiento());
            usuario.setEscuela(updateProfesorRequest.getEscuela());
            usuario.setCedula(updateProfesorRequest.getCedula());

            if (updateProfesorRequest.getPassword() != null && !updateProfesorRequest.getPassword().isEmpty())
                usuario.setPassword_hash(passwordEncoder.encode(updateProfesorRequest.getPassword()));

            profesorRepository.save(usuario);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }


    public ResponseEntity<Boolean> delete(Long id){
        Optional<Profesor> optionalUsuario = profesorRepository.findById(id);

        if (optionalUsuario.isPresent()){
            profesorRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

}
