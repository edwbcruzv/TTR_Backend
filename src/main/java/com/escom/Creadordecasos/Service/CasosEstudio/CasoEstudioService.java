package com.escom.Creadordecasos.Service.CasosEstudio;

import com.escom.Creadordecasos.Dto.CasoEstudioDTO;
import com.escom.Creadordecasos.Dto.GrupoDTO;
import com.escom.Creadordecasos.Entity.CasoEstudio;
import com.escom.Creadordecasos.Entity.Equipo;
import com.escom.Creadordecasos.Entity.Grupo;
import com.escom.Creadordecasos.Entity.Profesor;
import com.escom.Creadordecasos.Exception.BadRequestException;
import com.escom.Creadordecasos.Exception.NotFoundException;
import com.escom.Creadordecasos.Mapper.CasoEstudioMapper;
import com.escom.Creadordecasos.Repository.CasoEstudioRepository;
import com.escom.Creadordecasos.Repository.EquipoRepository;
import com.escom.Creadordecasos.Repository.ProfesorRepository;
import com.escom.Creadordecasos.Repository.RecursosMultimediaRepository;
import com.escom.Creadordecasos.Service.CasosEstudio.Bodies.CasoEstudioReq;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CasoEstudioService {
    private final CasoEstudioRepository casoEstudioRepository;
    private final ProfesorRepository profesorRepository;
    private final EquipoRepository equipoRepository;
    private final CasoEstudioMapper casoEstudioMapper;
    private final RecursosMultimediaRepository recursosMultimediaRepository;

    public ResponseEntity<List<CasoEstudioDTO>> getAllByProfesorId(Long id){
        Optional<Profesor> optionalProfesor = profesorRepository.findById(id);
        if (optionalProfesor.isPresent()){
            List<CasoEstudio> list_entity = casoEstudioRepository.findByProfesor(optionalProfesor.get());
            List<CasoEstudioDTO> list_dto = casoEstudioMapper.toListDto(list_entity);

            return ResponseEntity.ok(list_dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    // CREATE
    @Transactional
    public CasoEstudioDTO crear(CasoEstudioReq casoEstudioReq) throws BadRequestException {

        casoEstudioReq.setId(null);

        if(casoEstudioReq.getProfesor_id() == null) {
            throw  new BadRequestException();
        }

        Optional<Profesor> optionalProfesor = profesorRepository.findById(casoEstudioReq.getProfesor_id());
        if (optionalProfesor.isPresent()) {
            CasoEstudio casoEstudio = toEntity(casoEstudioReq);
            casoEstudio.setProfesor(optionalProfesor.get());
            casoEstudio.setFecha_creacion(new Date());

            CasoEstudio casoGuardado = casoEstudioRepository.save(casoEstudio);

            return casoToDto(casoGuardado);
        } else {
            throw new BadRequestException();
        }

    }

    // READ
    public List<CasoEstudioDTO> obtenerTodos() {
        List<CasoEstudio> list = casoEstudioRepository.findAll();
        List<CasoEstudioDTO> list_dto = casoEstudioMapper.toListDto(list);
        return list_dto;
    }
/*
    public List<CasoEstudioDTO> obtenerTodosVisibles() {
        List<CasoEstudio> lista = casoEstudioRepository.findAllByVisible(true);
        List<CasoEstudioDTO> listaDto = new ArrayList<>();
        for (CasoEstudio caso : lista) {
            listaDto.add(casoToDto(caso));
        }

        return listaDto;
    }**/

    public List<CasoEstudioDTO> obtenerPorDocente(Long id) {
        List<CasoEstudio> list = casoEstudioRepository.findByProfesores_Id(id);
        List<CasoEstudioDTO> list_dto = casoEstudioMapper.toListDto(list);
        return list_dto;
    }

    public CasoEstudioDTO obtenerPorId(Long id) throws NotFoundException {
        Optional<CasoEstudio> optional = casoEstudioRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException();
        }
        return casoToDto(optional.get());
    }

    // UPDATE
    public CasoEstudioDTO actualizar(CasoEstudioReq casoEstudioReq) throws NotFoundException, BadRequestException {
        Optional<CasoEstudio> optionalCasoEstudio = casoEstudioRepository.findById(casoEstudioReq.getId());
        if (optionalCasoEstudio.isEmpty()) {
            throw new NotFoundException();
        }

        Optional<Profesor> optionalProfesor = profesorRepository.findById(casoEstudioReq.getProfesor_id());
        if (optionalProfesor.isEmpty()){
            throw new NotFoundException();
        }

        CasoEstudio casoEstudio = toEntity(casoEstudioReq);
        casoEstudio.setProfesor(optionalProfesor.get());
        CasoEstudio casoGuardado = casoEstudioRepository.save(casoEstudio);
        return casoEstudioMapper.toDto(casoGuardado);
    }

    // DELETE
    public Boolean eliminar(Long id) throws NotFoundException {
        if (!casoEstudioRepository.existsById(id)) {
            throw new NotFoundException();
        }

        casoEstudioRepository.deleteById(id);
        return true;
    }

    public CasoEstudioDTO casoToDto(CasoEstudio caso) {
        CasoEstudioDTO dto = new CasoEstudioDTO();
        dto.setId(caso.getId());
        dto.setProfesor_id(caso.getProfesor().getId());
        dto.setTitulo(caso.getTitulo());
        dto.setIntroduccion(caso.getIntroduccion());
        dto.setResumen(caso.getResumen());
        dto.setObjetivos(caso.getObjetivos());
        dto.setClasificacion(caso.getClasificacion());
        dto.setLugar(caso.getLugar());
        dto.setTemporalidades(caso.getTemporalidades());
        dto.setProtagonistas(caso.getProtagonistas());
        dto.setOrganizaciones(caso.getOrganizaciones());
        dto.setPreguntas(caso.getPreguntas());
        dto.setRiesgos(caso.getRiesgos());
        dto.setResultados(caso.getResultados());
        dto.setConclusion(caso.getConclusion());
        dto.setComentarios(caso.getConclusion());
        dto.setFecha_creacion(caso.getFecha_creacion());

        return dto;
    }

    private CasoEstudio toEntity(CasoEstudioReq casoEstudioReq){



        List<Profesor> profesores;
        List<Long> profesores_ids = casoEstudioReq.getProfesores();
        if (profesores_ids !=null){
            profesores = profesorRepository.findByIdIn(casoEstudioReq.getProfesores());
            if (profesores == null){
                profesores = new ArrayList<Profesor>();
            }
        }else{
            profesores = new ArrayList<Profesor>();
        }

        List<Equipo> equipos;
        List<Long> equipos_ids = casoEstudioReq.getEquipos();
        if (equipos_ids !=null){
            equipos = equipoRepository.findByIdIn(casoEstudioReq.getEquipos());
            if (equipos == null){
                equipos = new ArrayList<Equipo>();
            }
        }else{
            equipos = new ArrayList<Equipo>();
        }



        CasoEstudio casoEstudio = CasoEstudio.builder()
                .id(casoEstudioReq.getId())
                .titulo(casoEstudioReq.getTitulo())
                .introduccion(casoEstudioReq.getIntroduccion())
                .resumen(casoEstudioReq.getResumen())

                .resumen_multimedia_list(recursosMultimediaRepository.findByIdIn(getOrCreateEmptyList(casoEstudioReq.getResumen_multimedia_list())))
                .objetivos(casoEstudioReq.getObjetivos())
                .objetivos_multimedia_list(recursosMultimediaRepository.findByIdIn(getOrCreateEmptyList(casoEstudioReq.getObjetivos_multimedia_list())))
                .clasificacion(casoEstudioReq.getClasificacion())
                .clasificacion_multimedia_list(recursosMultimediaRepository.findByIdIn(getOrCreateEmptyList(casoEstudioReq.getClasificacion_multimedia_list())))
                .lugar(casoEstudioReq.getLugar())
                .lugar_multimedia_list(recursosMultimediaRepository.findByIdIn(getOrCreateEmptyList(casoEstudioReq.getClasificacion_multimedia_list())))
                .temporalidades(casoEstudioReq.getTemporalidades())
                .temporalidades_multimedia_list(recursosMultimediaRepository.findByIdIn(getOrCreateEmptyList(casoEstudioReq.getTemporalidades_multimedia_list())))
                .protagonistas(casoEstudioReq.getProtagonistas())
                .protagonistas_multimedia_list(recursosMultimediaRepository.findByIdIn(getOrCreateEmptyList(casoEstudioReq.getProtagonistas_multimedia_list())))
                .organizaciones(casoEstudioReq.getOrganizaciones())
                .organizaciones_multimedia_list(recursosMultimediaRepository.findByIdIn(getOrCreateEmptyList(casoEstudioReq.getOrganizaciones_multimedia_list())))
                .preguntas(casoEstudioReq.getPreguntas())
                .preguntas_multimedia_list(recursosMultimediaRepository.findByIdIn(getOrCreateEmptyList(casoEstudioReq.getPreguntas_multimedia_list())))
                .riesgos(casoEstudioReq.getRiesgos())
                .riesgos_multimedia_list(recursosMultimediaRepository.findByIdIn(getOrCreateEmptyList(casoEstudioReq.getRiesgos_multimedia_list())))
                .resultados(casoEstudioReq.getResultados())
                .resultados_multimedia_list(recursosMultimediaRepository.findByIdIn(getOrCreateEmptyList(casoEstudioReq.getResultados_multimedia_list())))
                .anexos(casoEstudioReq.getAnexos())
                .anexos_multimedia_list(recursosMultimediaRepository.findByIdIn(getOrCreateEmptyList(casoEstudioReq.getAnexos_multimedia_list())))


                .conclusion(casoEstudioReq.getConclusion())
                .comentarios(casoEstudioReq.getComentarios())
                .fecha_creacion(casoEstudioReq.getFecha_creacion())
                .fecha_vencimiento(casoEstudioReq.getFecha_vencimiento())
                .profesores(profesores)
                .equipos(equipos)
                .build();

        return casoEstudio;
    }


    private List<Long> getOrCreateEmptyList(List<Long> inputList) {
        return inputList != null ? inputList : new ArrayList<Long>();
    }

}
