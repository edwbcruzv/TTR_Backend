package com.escom.Creadordecasos.Service.Inscripciones;

import com.escom.Creadordecasos.Dto.InscripcionDTO;
import com.escom.Creadordecasos.Entity.Estudiante;
import com.escom.Creadordecasos.Entity.Grupo;
import com.escom.Creadordecasos.Entity.Inscripcion;
import com.escom.Creadordecasos.Mapper.InscripcionMapper;
import com.escom.Creadordecasos.Repository.EstudianteRepository;
import com.escom.Creadordecasos.Repository.GrupoRepository;
import com.escom.Creadordecasos.Repository.InscripcionRepository;
import com.escom.Creadordecasos.Service.Inscripciones.Bodies.InscripcionReq;
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

    public ResponseEntity<InscripcionDTO> getById(Long id){
        Optional<Inscripcion> optionalInscripcion = inscripcionRepository.findById(id);
        if(optionalInscripcion.isPresent()) {
            InscripcionDTO dto = inscripcionMapper.toDto(optionalInscripcion.get());
            return ResponseEntity.ok(dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<InscripcionDTO> create(InscripcionReq inscripcionReq){
        Optional<Grupo> optionalGrupo = grupoRepository.findById(inscripcionReq.getGrupo_id());
        if (!optionalGrupo.isPresent()){
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(inscripcionReq.getEstudiante_id());
        if (!optionalEstudiante.isPresent()){
            return ResponseEntity.badRequest().body(null);
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
        Optional<Inscripcion> optionalInscripcion = inscripcionRepository.findById(inscripcionReq.getId());
        if(optionalInscripcion.isPresent()) {
            Inscripcion inscripcion = optionalInscripcion.get();
            Optional<Grupo> optionalGrupo = grupoRepository.findById(inscripcionReq.getGrupo_id());
            if (!optionalGrupo.isPresent()) {
                return ResponseEntity.badRequest().body(false);
            }

            Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(inscripcionReq.getEstudiante_id());
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

    public ResponseEntity<Boolean> delete(Long id){
        Optional<Inscripcion> optionalInscripcion = inscripcionRepository.findById(id);
        if(optionalInscripcion.isPresent()) {
            inscripcionRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.ok(false);
        }
    }


}
