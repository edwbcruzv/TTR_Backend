package com.escom.CreadorPracticas.Service.Inscripcion;

import com.escom.CreadorPracticas.Dto.GrupoDTO;
import com.escom.CreadorPracticas.Dto.InscripcionDTO;
import com.escom.CreadorPracticas.Entity.Estudiante;
import com.escom.CreadorPracticas.Entity.Grupo;
import com.escom.CreadorPracticas.Entity.Inscripcion;
import com.escom.CreadorPracticas.Entity.InscripcionKey;
import com.escom.CreadorPracticas.Mapper.GrupoMapper;
import com.escom.CreadorPracticas.Mapper.InscripcionMapper;
import com.escom.CreadorPracticas.Repository.EstudianteRepository;
import com.escom.CreadorPracticas.Repository.GrupoRepository;
import com.escom.CreadorPracticas.Repository.InscripcionRepository;
import com.escom.CreadorPracticas.Service.Inscripcion.Bodies.InscripcionReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InscripcionService {
    private final InscripcionRepository inscripcionRepository;
    private final InscripcionMapper inscripcionMapper;
    private final EstudianteRepository estudianteRepository;
    private final GrupoRepository grupoRepository;
    private final GrupoMapper grupoMapper;

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
                .inscripcionKey(InscripcionKey.builder()
                        .estudiante_username(optionalEstudiante.get().getUsername())
                        .grupo_id(optionalGrupo.get().getId())
                        .build())
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

    @Transactional
    public ResponseEntity<Boolean> delete(String username, Long grupoId){
        Optional<Inscripcion> optionalInscripcion = inscripcionRepository.findByEstudianteUsernameAndGrupoId(username,grupoId);
        if(optionalInscripcion.isPresent()) {
            try{
                inscripcionRepository.deleteByEstudianteUsernameAndGrupoId(username,grupoId);
            } catch (Exception e) {
                System.out.println(e);
                return ResponseEntity.badRequest().body(null);
            }

            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<List<InscripcionDTO>> getAllByEstudianteId(String username) {
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findByUsername(username);

        if (optionalEstudiante.isPresent()){
            List<Inscripcion> list = inscripcionRepository.findByEstudiante(optionalEstudiante.get());
            List<InscripcionDTO> list_dto = inscripcionMapper.toListDto(list);
            return ResponseEntity.ok(list_dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }


    public ResponseEntity<List<InscripcionDTO>> getAllByGrupoId(Long id) {
        Optional<Grupo> optionalGrupo = grupoRepository.findById(id);
        if(optionalGrupo.isPresent()) {
            List<Inscripcion> list = inscripcionRepository.findByGrupo(optionalGrupo.get());
            List<InscripcionDTO> list_dto = inscripcionMapper.toListDto(list);
            return ResponseEntity.ok(list_dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }
}
