package com.escom.CreadorPracticas.Service.Practica;

import com.escom.CreadorPracticas.Dto.PracticaDTO;
import com.escom.CreadorPracticas.Entity.*;
import com.escom.CreadorPracticas.Mapper.PracticaMapper;
import com.escom.CreadorPracticas.Repository.PracticaRepository;
import com.escom.CreadorPracticas.Repository.ProfesorRepository;
import com.escom.CreadorPracticas.Repository.RecursosMultimediaRepository;
import com.escom.CreadorPracticas.Repository.SolucionRepository;
import com.escom.CreadorPracticas.Service.Practica.Bodies.PracticaReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PracticaService {

    private final PracticaRepository practicaRepository;
    private final PracticaMapper practicaMapper;
    private final ProfesorRepository profesorRepository;
    private final RecursosMultimediaRepository recursosMultimediaRepository;
    private final SolucionRepository solucionRepository;


    public ResponseEntity<List<PracticaDTO>> getAll(){
        List<Practica> list = practicaRepository.findAll();
        List<PracticaDTO> list_dto = practicaMapper.toListDto(list);
        return ResponseEntity.ok(list_dto);
    }

    public ResponseEntity<List<PracticaDTO>> getAllByProfesorUsername(String username){
        Optional<Profesor> optionalProfesor = profesorRepository.findByUsername(username);

        if (optionalProfesor.isPresent()){
            List<Practica> list = practicaRepository.findByProfesor(optionalProfesor.get());
            List<PracticaDTO> list_dto = practicaMapper.toListDto(list);
            return ResponseEntity.ok(list_dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<PracticaDTO> get(Long id){
        Optional<Practica> optionalPractica = practicaRepository.findById(id);

        if (optionalPractica.isPresent()){
            return ResponseEntity.ok(practicaMapper.toDto(optionalPractica.get()));
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<PracticaDTO> create(PracticaReq practicaReq){
        Optional<Profesor> optionalProfesor = profesorRepository.findByUsername(practicaReq.getUsernameProfesor());

        Profesor profesor;
        if (optionalProfesor.isPresent()){
            profesor = optionalProfesor.get();
        }else{
            return ResponseEntity.badRequest().body(null);
        }

        Practica practica = Practica.builder()
                .profesor(profesor)
                .titulo(practicaReq.getTitulo())
                .descripcion(practicaReq.getDescripcion())
                .fechaCreacion(LocalDateTime.now())
                .soluciones(new ArrayList<Solucion>())
                .recursosMultimedia(new ArrayList<RecursoMultimedia>())
                .comentarios(practicaReq.getComentarios())
                .rubrica(practicaReq.getRubrica())
                .build();

        practicaRepository.save(practica);

        return ResponseEntity.ok(practicaMapper.toDto(practica));
    }

    public ResponseEntity<Boolean> update(PracticaReq practicaReq){
        Optional<Practica> optionalPractica = practicaRepository.findById(practicaReq.getId());

        if (optionalPractica.isPresent()){
            Practica practica = optionalPractica.get();

            practica.setTitulo(practicaReq.getTitulo());
            practica.setDescripcion(practicaReq.getDescripcion());
            practica.setSoluciones(solucionRepository.findByIdIn(getOrCreateEmptyList(practicaReq.getSoluciones())));
            practica.setRecursosMultimedia(recursosMultimediaRepository.findByIdIn(getOrCreateEmptyList( practicaReq.getRecursosMultimedia())));
            practica.setComentarios(practicaReq.getComentarios());
            practica.setRubrica(practicaReq.getRubrica());

            practicaRepository.save(practica);
            return ResponseEntity.ok(true);

        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Boolean> delete(Long id){
        Optional<Practica> optionalPractica = practicaRepository.findById(id);
        if(optionalPractica.isPresent()) {
            practicaRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.ok(false);
        }
    }

    private List<Long> getOrCreateEmptyList(List<Long> inputList) {
        return inputList != null ? inputList : new ArrayList<Long>();
    }
}
