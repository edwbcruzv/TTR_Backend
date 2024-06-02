package com.escom.CreadorPracticas.Service.Solucion;

import com.escom.CreadorPracticas.Dto.SolucionDTO;
import com.escom.CreadorPracticas.Dto.SolucionMinDTO;
import com.escom.CreadorPracticas.Entity.*;
import com.escom.CreadorPracticas.Mapper.SolucionMapper;
import com.escom.CreadorPracticas.Repository.*;
import com.escom.CreadorPracticas.Service.Solucion.Bodies.SolucionReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final ProfesorRepository profesorRepository;
    private final GrupoRepository grupoRepository;
    private final InscripcionRepository inscripcionRepository;


    public ResponseEntity<List<SolucionMinDTO>> getAll(){
        List<Solucion> list = solucionRepository.findAll();
        List<SolucionMinDTO> list_dto = solucionMapper.toListMinDto(list);
        return ResponseEntity.ok(list_dto);
    }

    public ResponseEntity<List<SolucionMinDTO>> getAllByEquipoId(Long id){
        Optional<Equipo> optionalEquipo = equipoRepository.findById(id);
        if(optionalEquipo.isPresent()) {
            List<Solucion> list = solucionRepository.findByEquipo(optionalEquipo.get());
            List<SolucionMinDTO> list_dto = solucionMapper.toListMinDto(list);
            return ResponseEntity.ok(list_dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<List<SolucionMinDTO>> getAllByEstudianteUsername(String username){
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findByUsername(username);
        if(optionalEstudiante.isPresent()) {
            List<Solucion> list = solucionRepository.findByEstudiante(optionalEstudiante.get());
            List<SolucionMinDTO> list_dto = solucionMapper.toListMinDto(list);
            return ResponseEntity.ok(list_dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<List<SolucionMinDTO>> getAllByProfesorUsernameAndGrupoIdByEquipos(String profesorUsername, Long grupoId){
        Optional<Profesor> optionalProfesor = profesorRepository.findByUsername(profesorUsername);
        if (optionalProfesor.isEmpty()){
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Grupo> optionalGrupo = grupoRepository.findById(grupoId);
        if (optionalGrupo.isEmpty()){
            return ResponseEntity.badRequest().body(null);
        }

        List<Practica> practica_list = practicaRepository.findByProfesor(optionalProfesor.get());
        List<Equipo> equipo_list = equipoRepository.findByGrupo(optionalGrupo.get());

        List<Solucion> solucion_list = solucionRepository.findByPracticaInAndEquipoIn(practica_list,equipo_list);
        List<SolucionMinDTO> dto_list = solucionMapper.toListMinDto(solucion_list);

        return ResponseEntity.ok(dto_list);
    }

    public ResponseEntity<List<SolucionMinDTO>> getAllByProfesorUsernameAndGrupoIdByIndividual(String profesorUsername, Long grupoId){
        Optional<Profesor> optionalProfesor = profesorRepository.findByUsername(profesorUsername);
        if (optionalProfesor.isEmpty()){
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Grupo> optionalGrupo = grupoRepository.findById(grupoId);
        if (optionalGrupo.isEmpty()){
            return ResponseEntity.badRequest().body(null);
        }

        List<Inscripcion> inscripcion_list = inscripcionRepository.findByGrupo(optionalGrupo.get());
        List<Estudiante> estudiante_list = new ArrayList<>();
        for (Inscripcion inscripcion:inscripcion_list){
            estudiante_list.add(inscripcion.getEstudiante());
        }

        List<Practica> practica_list = practicaRepository.findByProfesor(optionalProfesor.get());

        List<Solucion> solucion_list = solucionRepository.findByPracticaInAndEstudianteIn(practica_list,estudiante_list);
        List<SolucionMinDTO> dto_list = solucionMapper.toListMinDto(solucion_list);

        return ResponseEntity.ok(dto_list);

    }


    public ResponseEntity<SolucionDTO> get(Long id){
        Optional<Solucion> optionalSolucion = solucionRepository.findById(id);

        if (optionalSolucion.isPresent()){
            return ResponseEntity.ok(solucionMapper.toDto(optionalSolucion.get()));
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }
    /*
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

     */

    public ResponseEntity<Boolean> update(SolucionReq solucionReq){
        Optional<Solucion> optionalSolucion = solucionRepository.findById(solucionReq.getId());
        if (optionalSolucion.isPresent()){
            /*
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

             */

            Solucion solucion = optionalSolucion.get();

            //solucion.setPractica(practica);
            solucion.setStrHtml(solucionReq.getStrHtml());
            solucion.setStrCss(solucionReq.getStrCss());
            solucion.setStrJs(solucionReq.getStrJs());
            solucion.setConclusion(solucionReq.getConclusion());
            //solucion.setEstudiante(estudiante);
            //solucion.setEquipo(equipo);
            //solucion.setFechaLimiteEntrega(solucionReq.getFechaLimiteEntrega());
            solucion.setFechaUltimaEdicion(LocalDateTime.now());
            solucion.setRubricaCalificada(solucionReq.getRubricaCalificada());
            solucion.setCalificacion(solucion.getCalificacion());

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
