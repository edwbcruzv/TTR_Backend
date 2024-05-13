package com.escom.CreadorPracticas.Service.Practica;

import com.escom.CreadorPracticas.Dto.PracticaDTO;
import com.escom.CreadorPracticas.Entity.Practica;
import com.escom.CreadorPracticas.Entity.Profesor;
import com.escom.CreadorPracticas.Entity.RecursoMultimedia;
import com.escom.CreadorPracticas.Entity.Solucion;
import com.escom.CreadorPracticas.Repository.PracticaRepository;
import com.escom.CreadorPracticas.Repository.ProfesorRepository;
import com.escom.CreadorPracticas.Repository.RecursosMultimediaRepository;
import com.escom.CreadorPracticas.Service.Practica.Bodies.PracticaReq;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class PracticaService {

    private final PracticaRepository practicaRepository;
    private final ProfesorRepository profesorRepository;
    private final RecursosMultimediaRepository recursosMultimediaRepository;

    public ResponseEntity<PracticaDTO> create(PracticaReq req) {
        // Validamos los campos
        if(req.getTitulo() == null || req.getTitulo().isEmpty()  ||
            req.getRubrica() == null || req.getRubrica().isEmpty() ||
            req.getProfesorId() == null || !profesorRepository.existsById(req.getProfesorId())) {
            return ResponseEntity.badRequest().build();
        }

        for(Long multimediaId : req.getRecursosMultimedia()) {
            if(!recursosMultimediaRepository.existsById(multimediaId)) {
                return ResponseEntity.notFound().build();
            }
        }

        // Creamos el obj practica
        Practica practica  = Practica.builder()
                .profesor(profesorRepository.findById(req.getProfesorId()).orElse(null))
                .titulo(req.getTitulo())
                .descripcion(req.getDescripcion())
                .fechaCreacion(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .recursosMultimedia(recursosMultimediaRepository.findByIdIn(req.getRecursosMultimedia()))
                .comentarios(req.getComentarios())
                .rubrica(req.getRubrica())
                .build();

        practica = practicaRepository.save(practica);

        // Pasamos el obj creado a su DTO
        PracticaDTO res = PracticaDTO.builder()
                .id(practica.getId())
                .nombreprofesor(practica.getProfesor().getNombre())
                .titulo(practica.getTitulo())
                .descripcion(practica.getDescripcion())
                .recursosMultimedia(
                        practica.getRecursosMultimedia()
                                .stream()
                                .map(RecursoMultimedia::getId)
                                .collect(Collectors.toList())
                )
                .comentarios(practica.getComentarios())
                .rubrica(practica.getRubrica())
                .build();

        return ResponseEntity.ok(res);
    }

    public ResponseEntity<List<PracticaDTO>> getAll() {
        List<Practica> practicas = practicaRepository.findAll();
        List<PracticaDTO> res = new ArrayList<>();
        for(Practica p : practicas) {
            PracticaDTO dto = PracticaDTO.builder()
                    .id(p.getId())
                    .nombreprofesor(p.getProfesor().getNombre())
                    .titulo(p.getTitulo())
                    .descripcion(p.getDescripcion())
                    .fechaCreacion(p.getFechaCreacion())
                    .recursosMultimedia(
                            p.getRecursosMultimedia()
                                    .stream()
                                    .map(RecursoMultimedia::getId)
                                    .collect(Collectors.toList())
                    )
                    .soluciones(
                            p.getSoluciones()
                                    .stream()
                                    .map(Solucion::getId)
                                    .collect(Collectors.toList())
                    )
                    .comentarios(p.getComentarios())
                    .rubrica(p.getRubrica())
                    .build();
            res.add(dto);
        }
        return ResponseEntity.ok(res);
    }

    public ResponseEntity<PracticaDTO> getById(Long id) {
        Practica p = practicaRepository.findById(id).orElse(null);
        if(p == null) {
            return ResponseEntity.notFound().build();
        }

        PracticaDTO res = PracticaDTO.builder()
                .id(p.getId())
                .nombreprofesor(p.getProfesor().getNombre())
                .titulo(p.getTitulo())
                .descripcion(p.getDescripcion())
                .fechaCreacion(p.getFechaCreacion())
                .recursosMultimedia(
                        p.getRecursosMultimedia()
                                .stream()
                                .map(RecursoMultimedia::getId)
                                .collect(Collectors.toList())
                )
                .soluciones(
                        p.getSoluciones()
                                .stream()
                                .map(Solucion::getId)
                                .collect(Collectors.toList())
                )
                .comentarios(p.getComentarios())
                .rubrica(p.getRubrica())
                .build();

        return ResponseEntity.ok(res);
    }

    public ResponseEntity<PracticaDTO> update(PracticaReq req) {
        if(req.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        if(!practicaRepository.existsById(req.getId())) {
            return ResponseEntity.notFound().build();
        }

        Practica practica = new Practica();
        if(req.getProfesorId() != null) {
            Profesor profesor = profesorRepository.findById(req.getProfesorId()).orElse(null);

            practica.setProfesor(profesor);
        }

        if(req.getTitulo() != null && !req.getTitulo().isEmpty()) {
            practica.setTitulo(req.getTitulo());
        }

        if(req.getDescripcion() != null) {
            practica.setDescripcion(req.getDescripcion());
        }

        if(req.getRecursosMultimedia() != null && !req.getRecursosMultimedia().isEmpty()) {
            List<RecursoMultimedia> nuevosRecursos = new ArrayList<>();
            for(Long id : req.getRecursosMultimedia()) {
                RecursoMultimedia tmp = recursosMultimediaRepository.findById(id).orElse(null);
                if(tmp == null) {
                    return ResponseEntity.notFound().build();
                }
                nuevosRecursos.add(tmp);
            }

            practica.setRecursosMultimedia(nuevosRecursos);
        }

        if(req.getRubrica() != null) {
            practica.setRubrica(req.getRubrica());
        }

        practica = practicaRepository.save(practica);

        // Regresamos a DTO
        PracticaDTO res = new PracticaDTO();
        res.setId(practica.getId());
        res.setNombreprofesor(practica.getProfesor().getNombre());
        res.setTitulo(practica.getTitulo());
        res.setDescripcion(practica.getDescripcion());
        res.setFechaCreacion(practica.getFechaCreacion());
        res.setRecursosMultimedia(practica.getRecursosMultimedia().stream().map(RecursoMultimedia::getId).collect(Collectors.toList()));
        res.setSoluciones(practica.getSoluciones().stream().map(Solucion::getId).collect(Collectors.toList()));
        res.setComentarios(practica.getComentarios());
        res.setRubrica(practica.getRubrica());

        return ResponseEntity.ok(res);
    }

    public ResponseEntity<Boolean> delete(Long id) {
        if(!practicaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        practicaRepository.deleteById(id);

        return ResponseEntity.ok(true);
    }
}
