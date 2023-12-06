package com.escom.Creadordecasos.Service.CasosEstudio;

import com.escom.Creadordecasos.Dto.CasoEstudioDTO;
import com.escom.Creadordecasos.Entity.CasoEstudio;
import com.escom.Creadordecasos.Entity.Profesor;
import com.escom.Creadordecasos.Exception.BadRequestException;
import com.escom.Creadordecasos.Exception.NotFoundException;
import com.escom.Creadordecasos.Mapper.CasoEstudioMapper;
import com.escom.Creadordecasos.Repository.CasosEstudio.CasoEstudioRepository;
import com.escom.Creadordecasos.Repository.Profesores.ProfesorRepository;
import com.escom.Creadordecasos.Service.CasosEstudio.Bodies.CasoEstudioReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CasoEstudioService {
    private final CasoEstudioRepository casoEstudioRepository;
    private final ProfesorRepository profesorRepository;
    private final CasoEstudioMapper casoEstudioMapper;

    // CREATE
    public CasoEstudioDTO crear(CasoEstudioReq casoEstudioReq) throws BadRequestException {

        Optional<Profesor> profesor;
        for (long id : casoEstudioReq.getProfesores()) {
            profesor = profesorRepository.findById(id);
            if (profesor.isEmpty()){
                throw new BadRequestException();
            }
        }
        CasoEstudio casoEstudio = casoEstudioMapper.toEntity(casoEstudioReq);
        casoEstudio.setFecha_creacion(new Date());

        CasoEstudio casoGuardado = casoEstudioRepository.save(casoEstudio);

        return casoToDto(casoGuardado);
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
        if (casoEstudioReq.getId() == null) {
            throw new BadRequestException();
        }

        if (!casoEstudioRepository.existsById(casoEstudioReq.getId())) {
            throw new NotFoundException();
        }

        if (casoEstudioReq.getProfesores() != null) {
            Optional<Profesor> profesor;
            for (long id : casoEstudioReq.getProfesores()) {
                profesor = profesorRepository.findById(id);
                if (profesor.isEmpty()){
                    throw new BadRequestException();
                }
            }
        }

        CasoEstudio casoEstudio = casoEstudioMapper.toEntity(casoEstudioReq);

        CasoEstudio casoGuardado = casoEstudioRepository.save(casoEstudio);
        return casoToDto(casoGuardado);
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
}
