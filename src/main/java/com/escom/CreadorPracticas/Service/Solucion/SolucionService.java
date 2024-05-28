package com.escom.CreadorPracticas.Service.Solucion;

import com.escom.CreadorPracticas.Dto.SolucionDTO;
import com.escom.CreadorPracticas.Entity.*;
import com.escom.CreadorPracticas.Mapper.SolucionMapper;
import com.escom.CreadorPracticas.Repository.*;
import com.escom.CreadorPracticas.Service.Solucion.Bodies.SolucionReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SolucionService {

    private final SolucionRepository solucionRepository;
    private final SolucionMapper solucionMapper;
    private final EquipoRepository equipoRepository;
    private final PracticaRepository practicaRepository;
    private final EstudianteRepository estudianteRepository;
    private final GrupoRepository grupoRepository;


    public ResponseEntity<List<SolucionDTO>> getAll(){
        List<Solucion> list = solucionRepository.findAll();
        List<SolucionDTO> list_dto = solucionMapper.toListDto(list);
        return ResponseEntity.ok(list_dto);
    }

    public ResponseEntity<List<SolucionDTO>> getAllByEquipoId(Long id){
        Optional<Equipo> optionalEquipo = equipoRepository.findById(id);
        if(optionalEquipo.isPresent()) {
            List<Solucion> list = solucionRepository.findByEquipo(optionalEquipo.get());
            List<SolucionDTO> list_dto = solucionMapper.toListDto(list);
            return ResponseEntity.ok(list_dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    /*
    public ResponseEntity<List<SolucionDTO>> getAllByGrupoId(Long id){
        Optional<Grupo> optionalGrupo = grupoRepository.findById(id);
        if(optionalGrupo.isPresent()) {
            List<Solucion> list = solucionRepository.findByGrupo(optionalGrupo.get());


            List<SolucionDTO> list_dto = solucionMapper.toListDto(list);
            return ResponseEntity.ok(list_dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }*/

    public ResponseEntity<SolucionDTO> get(Long id){
        Optional<Solucion> optionalSolucion = solucionRepository.findById(id);

        if (optionalSolucion.isPresent()){
            return ResponseEntity.ok(solucionMapper.toDto(optionalSolucion.get()));
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<SolucionDTO> create(SolucionReq solucionReq){
        Optional<Equipo> optionalEquipo = equipoRepository.findById(solucionReq.getEquipoId());

        Equipo equipo;
        if (optionalEquipo.isPresent()){
            equipo = optionalEquipo.get();
        }else{
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Practica> optionalPractica = practicaRepository.findById(solucionReq.getPracticaId());

        Practica practica;
        if (optionalPractica.isPresent()){
            practica = optionalPractica.get();
        }else{
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Estudiante> optionalEstudiante = estudianteRepository.findByUsername(solucionReq.getEstudianteUsername());
        Estudiante estudiante;
        if (optionalEstudiante.isPresent()){
            estudiante = optionalEstudiante.get();
        }else{
            estudiante = null;
        }

        Solucion solucion = Solucion.builder()
                .practica(practica)
                .strHtml(solucionReq.getStrHtml())
                .strCss(solucionReq.getStrCss())
                .strJs(solucionReq.getStrJs())
                .conclusion(solucionReq.getConclusion())
                .estudiante(estudiante)
                .equipo(equipo)
                .fechaLimiteEntrega(solucionReq.getFechaLimiteEntrega())
                .fechaUltimaEdicion(LocalDateTime.now())
                .rubricaCalificada(solucionReq.getRubricaCalificada())
                .build();

        solucionRepository.save(solucion);

        return ResponseEntity.ok(solucionMapper.toDto(solucion));
    }

    public ResponseEntity<Boolean> update(SolucionReq solucionReq){
        Optional<Solucion> optionalSolucion = solucionRepository.findById(solucionReq.getId());
        if (optionalSolucion.isPresent()){

            Optional<Equipo> optionalEquipo = equipoRepository.findById(solucionReq.getEquipoId());

            Equipo equipo;
            if (optionalEquipo.isPresent()){
                equipo = optionalEquipo.get();
            }else{
                return ResponseEntity.badRequest().body(null);
            }

            Optional<Practica> optionalPractica = practicaRepository.findById(solucionReq.getPracticaId());

            Practica practica;
            if (optionalPractica.isPresent()){
                practica = optionalPractica.get();
            }else{
                return ResponseEntity.badRequest().body(null);
            }

            Optional<Estudiante> optionalEstudiante = estudianteRepository.findByUsername(solucionReq.getEstudianteUsername());
            Estudiante estudiante;
            if (optionalEstudiante.isPresent()){
                estudiante = optionalEstudiante.get();
            }else{
                estudiante = null;
            }

            Solucion solucion = optionalSolucion.get();

            solucion.setPractica(practica);
            solucion.setStrHtml(solucionReq.getStrHtml());
            solucion.setStrCss(solucionReq.getStrCss());
            solucion.setStrJs(solucionReq.getStrJs());
            solucion.setConclusion(solucionReq.getConclusion());
            solucion.setEstudiante(estudiante);
            solucion.setEquipo(equipo);
            solucion.setFechaLimiteEntrega(solucionReq.getFechaLimiteEntrega());
            solucion.setFechaUltimaEdicion(LocalDateTime.now());
            solucion.setRubricaCalificada(solucionReq.getRubricaCalificada());

            solucionRepository.save(solucion);

            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Boolean> delete(Long id){
        Optional<Solucion> optionalSolucion = solucionRepository.findById(id);
        if(optionalSolucion.isPresent()) {
            solucionRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.ok(false);
        }
    }
}
