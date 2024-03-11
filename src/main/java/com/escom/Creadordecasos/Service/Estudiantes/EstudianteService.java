package com.escom.Creadordecasos.Service.Estudiantes;

import com.escom.Creadordecasos.Dto.EstudianteDTO;
import com.escom.Creadordecasos.Entity.Equipo;
import com.escom.Creadordecasos.Entity.Estudiante;
import com.escom.Creadordecasos.Entity.Usuario;
import com.escom.Creadordecasos.Exception.BadRequestException;
import com.escom.Creadordecasos.Mapper.EstudianteMapper;
import com.escom.Creadordecasos.Repository.EquipoRepository;
import com.escom.Creadordecasos.Repository.EstudianteRepository;
import com.escom.Creadordecasos.Repository.GrupoRepository;
import com.escom.Creadordecasos.Service.Estudiantes.Bodies.SolucionReq;
import com.escom.Creadordecasos.Service.Estudiantes.Bodies.UpdateEstudianteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para los usuarios
 */
@Service
@RequiredArgsConstructor
public class EstudianteService {
    private final GrupoRepository grupoRepository;
    private final PasswordEncoder passwordEncoder;
    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;
    private final EquipoRepository equipoRepository;
    public ResponseEntity<List<EstudianteDTO>> getAll(){

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //User userAuthenticated = (User) authentication.getPrincipal();
        List<Estudiante> usuarioList = estudianteRepository.findAll();
        List<EstudianteDTO> list_dto = estudianteMapper.toListDto(usuarioList);
        return ResponseEntity.ok(list_dto);
    }

    public ResponseEntity<List<EstudianteDTO>> getEstudiantesByIds(List<String> usernames) {
        List<Estudiante> usuarioList = estudianteRepository.findEstudiantesByUsernames(usernames);
        List<EstudianteDTO> list_dto = estudianteMapper.toListDto(usuarioList);
        return ResponseEntity.ok(list_dto);
    }
/*
    public ResponseEntity<List<EstudianteDTO>> getAllByGroupId(Long id) {
        List<Estudiante> usuarioList = estudianteRepository.findByGrupoId(id);
        List<EstudianteDTO> list_dto = estudianteMapper.toListDto(usuarioList);
        return ResponseEntity.ok(list_dto);
    }

    public ResponseEntity<List<EstudianteDTO>> getAllByGroupIdAndNotTeam(Long id) {

        List<Estudiante> usuarioList = estudianteRepository.findEstudiantesByGrupoWithoutEquipo(id);
        List<EstudianteDTO> list_dto = estudianteMapper.toListDto(usuarioList);
        return ResponseEntity.ok(list_dto);
    }
*/
    public ResponseEntity<EstudianteDTO> get(String username){
        Optional<Estudiante> optionalUsuario = estudianteRepository.findByUsername(username);

        if (optionalUsuario.isPresent()){
            return ResponseEntity.ok(estudianteMapper.toDto(optionalUsuario.get()));
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }
    public ResponseEntity<Boolean> update(UpdateEstudianteRequest updateEstudianteRequest){
        Optional<Estudiante> optionalUsuario = estudianteRepository.findByUsername(updateEstudianteRequest.getUsername());

        if (optionalUsuario.isPresent()) {
            Estudiante usuario = optionalUsuario.get();


            usuario.setUsername(updateEstudianteRequest.getUsername());
            usuario.setEmail(updateEstudianteRequest.getEmail());
            usuario.setNombre(updateEstudianteRequest.getNombre());
            usuario.setApellidoPaterno(updateEstudianteRequest.getApellidoPaterno());
            usuario.setApellidoMaterno(updateEstudianteRequest.getApellidoMaterno());
            usuario.setFechaNacimiento(updateEstudianteRequest.getFechaNacimiento());
            usuario.setBoleta(updateEstudianteRequest.getBoleta());

            if (updateEstudianteRequest.getPassword() != null && !updateEstudianteRequest.getPassword().isEmpty())
                usuario.setPasswordHash(passwordEncoder.encode(updateEstudianteRequest.getPassword()));

            estudianteRepository.save(usuario);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }


    public ResponseEntity<Boolean> delete(String username){
        Optional<Estudiante> optionalUsuario = estudianteRepository.findByUsername(username);

        if (optionalUsuario.isPresent()){
            estudianteRepository.deleteByUsername(optionalUsuario.get().getUsername());
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }
}

