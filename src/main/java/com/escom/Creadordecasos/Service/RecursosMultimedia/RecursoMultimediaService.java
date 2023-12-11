package com.escom.Creadordecasos.Service.RecursosMultimedia;

import com.escom.Creadordecasos.Dto.RecursoMultimediaDTO;
import com.escom.Creadordecasos.Entity.CasoEstudio;
import com.escom.Creadordecasos.Entity.RecursoMultimedia;
import com.escom.Creadordecasos.Mapper.RecursoMultimediaMapper;
import com.escom.Creadordecasos.Repository.CasoEstudioRepository;
import com.escom.Creadordecasos.Repository.RecursosMultimediaRepository;
import com.escom.Creadordecasos.Service.FilesManager.FilesManagerService;
import com.escom.Creadordecasos.Service.RecursosMultimedia.Bodies.RecursoMultimediaReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecursoMultimediaService {

    private final RecursosMultimediaRepository recursosMultimediaRepository;
    private final FilesManagerService filesManagerService;
    private final CasoEstudioRepository casoEstudioRepository;
    private final RecursoMultimediaMapper recursoMultimediaMapper;

    public ResponseEntity<List<RecursoMultimediaDTO>> getAllByListId(List<Long> list){
        List<RecursoMultimedia> list_entity = recursosMultimediaRepository.findByIdIn(list);
        List<RecursoMultimediaDTO> list_dto = recursoMultimediaMapper.toListDto(list_entity);
        return ResponseEntity.ok(list_dto);
    }

    public ResponseEntity<RecursoMultimediaDTO> getById(Long id){
        Optional<RecursoMultimedia> recursoMultimedia = recursosMultimediaRepository.findById(id);
        if(recursoMultimedia.isPresent()) {
            RecursoMultimediaDTO dto = recursoMultimediaMapper.toDto(recursoMultimedia.get());

            return ResponseEntity.ok(dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<RecursoMultimediaDTO> create(RecursoMultimediaReq recursoMultimediaReq){
        Optional<CasoEstudio> optionalCasoEstudio = casoEstudioRepository.findById(recursoMultimediaReq.getCaso_estudio_id());

        if(optionalCasoEstudio.isPresent()) {
            RecursoMultimedia recursoMultimedia = RecursoMultimedia.builder()
                    .titulo(recursoMultimediaReq.getTitulo())
                    .descripcion(recursoMultimediaReq.getDescripcion())
                    .path_src(filesManagerService.saveMultimedia(recursoMultimediaReq.getArchivoMultimedia(), recursoMultimediaReq.getUsuario_id(), recursoMultimediaReq.getCaso_estudio_id()))
                    .numero_orden(recursoMultimediaReq.getNumero_orden())
                    .caso_estudio(optionalCasoEstudio.get())
                    .build();

            recursosMultimediaRepository.save(recursoMultimedia);

            RecursoMultimediaDTO dto = recursoMultimediaMapper.toDto(recursoMultimedia);

            return ResponseEntity.ok(dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Boolean> update(RecursoMultimediaReq recursoMultimediaReq){
        Optional<RecursoMultimedia> optionalRecursoMultimedia = recursosMultimediaRepository.findById(recursoMultimediaReq.getId());
        if(optionalRecursoMultimedia.isPresent()) {
            RecursoMultimedia recursoMultimedia = optionalRecursoMultimedia.get();
            Optional<CasoEstudio> optionalCasoEstudio = casoEstudioRepository.findById(recursoMultimediaReq.getCaso_estudio_id());
            if (optionalCasoEstudio.isPresent()) {

                recursoMultimedia.setDescripcion(recursoMultimediaReq.getDescripcion());
                recursoMultimedia.setTitulo(recursoMultimediaReq.getTitulo());
                recursoMultimedia.setPath_src(recursoMultimedia.getPath_src());
                recursoMultimedia.setNumero_orden(recursoMultimedia.getNumero_orden());
                recursoMultimedia.setCaso_estudio(optionalCasoEstudio.get());

                try {
                    filesManagerService.updateMultimedia(recursoMultimedia.getPath_src(),recursoMultimediaReq.getArchivoMultimedia());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                recursosMultimediaRepository.save(recursoMultimedia);
                RecursoMultimediaDTO dto = recursoMultimediaMapper.toDto(recursoMultimedia);

                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.badRequest().body(false);
            }
        }else{
            return ResponseEntity.badRequest().body(false);
        }
    }

    public ResponseEntity<Boolean> delete(Long id){
        Optional<RecursoMultimedia> optionalRecursoMultimedia = recursosMultimediaRepository.findById(id);
        if(optionalRecursoMultimedia.isPresent()) {
            filesManagerService.deleteMultimedia(optionalRecursoMultimedia.get().getPath_src());
            recursosMultimediaRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.ok(false);
        }

    }
}
