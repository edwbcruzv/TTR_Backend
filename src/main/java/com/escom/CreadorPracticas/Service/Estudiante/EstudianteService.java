package com.escom.CreadorPracticas.Service.Estudiante;

import com.escom.CreadorPracticas.Entity.Equipo;
import com.escom.CreadorPracticas.Entity.Estudiante;
import com.escom.CreadorPracticas.Entity.Grupo;
import com.escom.CreadorPracticas.Entity.Inscripcion;
import com.escom.CreadorPracticas.Repository.EquipoRepository;
import com.escom.CreadorPracticas.Repository.EstudianteRepository;
import com.escom.CreadorPracticas.Repository.GrupoRepository;
import com.escom.CreadorPracticas.Repository.InscripcionRepository;
import com.escom.CreadorPracticas.Service.Estudiante.Bodies.UpdateEstudianteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private final InscripcionRepository inscripcionRepository;
    private final EquipoRepository equipoRepository;
    public ResponseEntity<List<Estudiante>> getAll(){

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //User userAuthenticated = (User) authentication.getPrincipal();
        List<Estudiante> usuarioList = estudianteRepository.findAll();
        return ResponseEntity.ok(usuarioList);
    }

    public ResponseEntity<List<Estudiante>> getEstudiantesByIds(List<String> usernames) {
        List<Estudiante> usuarioList = estudianteRepository.findEstudiantesByUsernames(usernames);
        return ResponseEntity.ok(usuarioList);
    }

    public ResponseEntity<List<Estudiante>> getAllByGroupId(Long id) {
        Optional<Grupo> optionalGrupo = grupoRepository.findById(id);
        if(optionalGrupo.isPresent()) {
            List<Inscripcion> list = inscripcionRepository.findByGrupo(optionalGrupo.get());
            List<Estudiante> estudianteList = new ArrayList<Estudiante>();
            for (Inscripcion inscripcion:list){
                estudianteList.add(inscripcion.getEstudiante());
            }
            return ResponseEntity.ok(estudianteList);
        }else{
            return ResponseEntity.badRequest().body(null);
        }


    }

    public ResponseEntity<List<Estudiante>> getAllByGroupIdAndNotTeam(Long id) {

        Optional<Grupo> optionalGrupo = grupoRepository.findById(id);
        if(optionalGrupo.isPresent()) {
            List<Inscripcion> list = inscripcionRepository.findByGrupo(optionalGrupo.get());
            List<Estudiante> estudianteList = new ArrayList<Estudiante>();
            for (Inscripcion inscripcion:list){
                if(!estudianteExistEquipoInGrupo(inscripcion.getEstudiante(),optionalGrupo.get())){
                    estudianteList.add(inscripcion.getEstudiante());
                }
            }
            return ResponseEntity.ok(estudianteList);
        }else{
            return ResponseEntity.badRequest().body(null);
        }


    }

    public ResponseEntity<Estudiante> get(String username){
        Optional<Estudiante> optionalUsuario = estudianteRepository.findByUsername(username);

        if (optionalUsuario.isPresent()){
            return ResponseEntity.ok(optionalUsuario.get());
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

    @Transactional
    public ResponseEntity<Boolean> delete(String username){
        Optional<Estudiante> optionalUsuario = estudianteRepository.findByUsername(username);

        if (optionalUsuario.isPresent()){
            estudianteRepository.deleteByUsername(optionalUsuario.get().getUsername());
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public boolean estudianteExistEquipoInGrupo(Estudiante estudiante, Grupo grupo) {

        // Obtener la lista de equipos del grupo proporcionado
        List<Equipo> equiposGrupo = grupo.getEquipos();

        // Obtener la lista de equipos del estudiante
        List<Equipo> equiposEstudiante = estudiante.getEquipos();

        // Verificar si hay algún equipo del grupo en la lista de equipos del estudiante
        for (Equipo equipoGrupo : equiposGrupo) {
            if (equiposEstudiante.contains(equipoGrupo)) {
                return true;
            }
        }

        // No se encontró ningún equipo del grupo en la lista de equipos del estudiante
        return false;
    }
}

