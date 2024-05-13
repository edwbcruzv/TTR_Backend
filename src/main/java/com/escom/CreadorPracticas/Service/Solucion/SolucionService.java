package com.escom.CreadorPracticas.Service.Solucion;

import com.escom.CreadorPracticas.Dto.SolucionDTO;
import com.escom.CreadorPracticas.Entity.Solucion;
import com.escom.CreadorPracticas.Repository.EquipoRepository;
import com.escom.CreadorPracticas.Repository.EstudianteRepository;
import com.escom.CreadorPracticas.Repository.PracticaRepository;
import com.escom.CreadorPracticas.Repository.SolucionRepository;
import com.escom.CreadorPracticas.Service.Solucion.Bodies.SolucionReq;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class SolucionService {
    private final SolucionRepository solucionRepository;
    private final PracticaRepository practicaRepository;
    private final EquipoRepository equipoRepository;
    private final EstudianteRepository estudianteRepository;

    public ResponseEntity<SolucionDTO> create(SolucionReq req) {
        if(!practicaRepository.existsById(req.getPracticaId())) {
            return ResponseEntity.notFound().build();
        }

        
        if(req.getEquipoId() != null && !equipoRepository.existsById(req.getEquipoId())) {
            return ResponseEntity.notFound().build();
        }
        
        if(req.getEstudianteUsername() != null && !estudianteRepository.existsByUsername(req.getEstudianteUsername())) {
            return ResponseEntity.notFound().build();
        }
        
        Solucion s = Solucion.builder()
                .practica(practicaRepository.findById(req.getPracticaId()).orElse(null))
                .strHtml(req.getStrHtml())
                .strCss(req.getStrCss())
                .comentarios(req.getComentarios())
                .estudiante(estudianteRepository.findByUsername(req.getEstudianteUsername()).orElse(null))
                .equipo(equipoRepository.findById(req.getEquipoId()).orElse(null))
                .fechaUltimaEdicion(LocalDateTime.now())
                .fechaLimiteEntrega(req.getFechaLimiteEntrega())
                .fechaEntrega(LocalDateTime.now())
                .build();

        SolucionDTO resp = getSolucionDTO(s);

        return ResponseEntity.ok(resp);
    }

    public ResponseEntity<List<SolucionDTO>> getAll() {
        List<Solucion> soluciones  = solucionRepository.findAll();
        List<SolucionDTO> resp = new ArrayList<>();
        for(Solucion s : soluciones) {
            SolucionDTO dto = getSolucionDTO(s);
            resp.add(dto);
        }

        return ResponseEntity.ok(resp);
    }

    public ResponseEntity<SolucionDTO> getById(Long id) {
        Solucion s = solucionRepository.findById(id).orElse(null);
        if(s == null) {
            return ResponseEntity.notFound().build();
        }
        SolucionDTO resp = getSolucionDTO(s);
        return ResponseEntity.ok(resp);
    }

    public ResponseEntity<SolucionDTO> update(SolucionReq req) {
        if(req.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        if(!solucionRepository.existsById(req.getId())) {
            return ResponseEntity.notFound().build();
        }


        Solucion s = Solucion.builder()
                .practica(req.getPracticaId() != null
                        ? practicaRepository.findById(req.getPracticaId()).orElse(null)
                        : null)
                .strHtml(req.getStrHtml())
                .strCss(req.getStrCss())
                .comentarios(req.getComentarios())
                .estudiante(req.getEstudianteUsername() != null
                        ? estudianteRepository.getByUsername(req.getEstudianteUsername())
                        : null)
                .equipo(req.getEquipoId() != null
                        ? equipoRepository.findById(req.getEquipoId()).orElse(null)
                        : null)
                .fechaUltimaEdicion(req.getFechaUltimaEdicion())
                .fechaLimiteEntrega(req.getFechaLimiteEntrega())
                .fechaEntrega(req.getFechaEntrega())
                .rubricaCalificada(req.getRubricaCalificada())
                .build();

        s = solucionRepository.save(s);
        SolucionDTO resp = getSolucionDTO(s);

        return ResponseEntity.ok(resp);
    }

    public ResponseEntity<Boolean> delete(Long id) {
        if(!solucionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(true);
    }

    private static SolucionDTO getSolucionDTO(Solucion s) {
        SolucionDTO resp = new SolucionDTO();
        resp.setId(s.getId());
        resp.setPracticaId(s.getPractica().getId());
        resp.setStrHtml(s.getStrHtml());
        resp.setStrCss(s.getStrCss());
        resp.setComentarios(s.getComentarios());
        resp.setEstudianteUsername(s.getEstudiante().getUsername());
        resp.setEquipoId(s.getEquipo().getId());
        resp.setFechaUltimaEdicion(s.getFechaUltimaEdicion());
        resp.setFechaLimiteEntrega(s.getFechaLimiteEntrega());
        resp.setFechaEntrega(s.getFechaEntrega());
        resp.setRubricaCalificada(s.getRubricaCalificada());
        return resp;
    }
}
