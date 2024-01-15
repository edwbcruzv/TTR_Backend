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

    public ResponseEntity<List<EstudianteDTO>> getEstudiantesByIds(List<Long> ids) {
        List<Estudiante> usuarioList = estudianteRepository.findEstudiantesByIds(ids);
        List<EstudianteDTO> list_dto = estudianteMapper.toListDto(usuarioList);
        return ResponseEntity.ok(list_dto);
    }

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

    public ResponseEntity<EstudianteDTO> get(Long id){
        Optional<Estudiante> optionalUsuario = estudianteRepository.findById(id);

        if (optionalUsuario.isPresent()){
            return ResponseEntity.ok(estudianteMapper.toDto(optionalUsuario.get()));
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }
    public ResponseEntity<Boolean> update(UpdateEstudianteRequest updateEstudianteRequest){
        Optional<Estudiante> optionalUsuario = estudianteRepository.findById(updateEstudianteRequest.getId());

        if (optionalUsuario.isPresent()) {
            Estudiante usuario = optionalUsuario.get();


            usuario.setUsername(updateEstudianteRequest.getUsername());
            usuario.setEmail(updateEstudianteRequest.getEmail());
            usuario.setNombre(updateEstudianteRequest.getNombre());
            usuario.setApellido_paterno(updateEstudianteRequest.getApellido_paterno());
            usuario.setApellido_materno(updateEstudianteRequest.getApellido_materno());
            usuario.setFecha_nacimiento(updateEstudianteRequest.getFecha_nacimiento());
            usuario.setSemestre(updateEstudianteRequest.getSemestre());
            usuario.setBoleta(updateEstudianteRequest.getBoleta());

            if (updateEstudianteRequest.getPassword() != null && !updateEstudianteRequest.getPassword().isEmpty())
                usuario.setPassword_hash(passwordEncoder.encode(updateEstudianteRequest.getPassword()));

            estudianteRepository.save(usuario);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }


    public ResponseEntity<Boolean> delete(Long id){
        Optional<Estudiante> optionalUsuario = estudianteRepository.findById(id);

        if (optionalUsuario.isPresent()){
            estudianteRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Boolean> agregarSolucion(SolucionReq solucionReq) {
        try {
            Optional<Equipo> opEquipo = equipoRepository.findById(solucionReq.getIdEquipo());
            if(opEquipo.isEmpty()) {
                throw new BadRequestException();
            }

            Optional<Estudiante> opEstudiante = estudianteRepository.findById(solucionReq.getUid());
            if(opEstudiante.isEmpty()) {
                throw new BadRequestException();
            }

            Equipo equipo = opEquipo.get();
            Estudiante estudiante = opEstudiante.get();

            List<Estudiante> estudiantes = equipo.getEstudiantes();

            if(!estudiantes.contains(estudiante)) {
                System.out.println("El estudiante no pertenece al equipo");
                throw new BadRequestException();
            }

            equipo.setSolucion(solucionReq.getSolucion());
            equipoRepository.updateSolucionById(solucionReq.getSolucion(), equipo.getId());

            return ResponseEntity.ok(true);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
    }


}

