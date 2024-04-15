package com.escom.CreadorPracticas.Service.Inscripcion;

import com.escom.CreadorPracticas.Dto.InscripcionDTO;
import com.escom.CreadorPracticas.Entity.Estudiante;
import com.escom.CreadorPracticas.Entity.Grupo;
import com.escom.CreadorPracticas.Entity.Inscripcion;
import com.escom.CreadorPracticas.Mapper.InscripcionMapper;
import com.escom.CreadorPracticas.Repository.EstudianteRepository;
import com.escom.CreadorPracticas.Repository.GrupoRepository;
import com.escom.CreadorPracticas.Repository.InscripcionRepository;
import com.escom.CreadorPracticas.Service.Inscripcion.Bodies.InscripcionReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InscripcionService {
    private final InscripcionRepository inscripcionRepository;
    private final InscripcionMapper inscripcionMapper;
    private final EstudianteRepository estudianteRepository;
    private final GrupoRepository grupoRepository;

    public ResponseEntity<InscripcionDTO> getById(String username, Long grupoId){
        Optional<Inscripcion> optionalInscripcion = inscripcionRepository.findByEstudianteUsernameAndGrupoId(username,grupoId);
        if(optionalInscripcion.isPresent()) {
            InscripcionDTO dto = inscripcionMapper.toDto(optionalInscripcion.get());
            return ResponseEntity.ok(dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<InscripcionDTO> create(InscripcionReq inscripcionReq){
        Optional<Grupo> optionalGrupo = grupoRepository.findByCodigo(inscripcionReq.getCodigo());
        if (!optionalGrupo.isPresent()){
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Estudiante> optionalEstudiante = estudianteRepository.findByUsername(inscripcionReq.getEstudianteUsername());
        if (!optionalEstudiante.isPresent()){
            return ResponseEntity.badRequest().body(null);
        }

        if(inscripcionRepository.existsByEstudianteUsernameAndGrupoId(optionalEstudiante.get().getUsername(), optionalGrupo.get().getId())) {
            return ResponseEntity.badRequest().build();
        }

        Inscripcion inscripcion = Inscripcion.builder()
                .grupo(optionalGrupo.get())
                .estudiante(optionalEstudiante.get())
                .build();
        inscripcionRepository.save(inscripcion);

        InscripcionDTO dto = inscripcionMapper.toDto(inscripcion);
        return ResponseEntity.ok(dto);

    }

    public ResponseEntity<Boolean> update(InscripcionReq inscripcionReq){
        Optional<Inscripcion> optionalInscripcion = inscripcionRepository.findByEstudianteUsernameAndGrupoId(inscripcionReq.getEstudianteUsername(),inscripcionReq.getGrupoId());
        if(optionalInscripcion.isPresent()) {
            Inscripcion inscripcion = optionalInscripcion.get();
            Optional<Grupo> optionalGrupo = grupoRepository.findById(inscripcionReq.getGrupoId());
            if (!optionalGrupo.isPresent()) {
                return ResponseEntity.badRequest().body(false);
            }

            Optional<Estudiante> optionalEstudiante = estudianteRepository.findByUsername(inscripcionReq.getEstudianteUsername());
            if (!optionalEstudiante.isPresent()) {
                return ResponseEntity.badRequest().body(false);
            }

            inscripcion.setGrupo(optionalGrupo.get());
            inscripcion.setEstudiante(optionalEstudiante.get());

            inscripcionRepository.save(inscripcion);

            return ResponseEntity.ok(true);

        }else{
            return ResponseEntity.badRequest().body(false);
        }

    }

    public ResponseEntity<Boolean> delete(String username, Long grupoId){
        Optional<Inscripcion> optionalInscripcion = inscripcionRepository.findByEstudianteUsernameAndGrupoId(username,grupoId);
        if(optionalInscripcion.isPresent()) {
            inscripcionRepository.deleteByEstudianteUsernameAndGrupoId(username,grupoId);
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.ok(false);
        }
    }
}
