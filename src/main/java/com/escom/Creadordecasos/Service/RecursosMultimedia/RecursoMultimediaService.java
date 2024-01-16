package com.escom.Creadordecasos.Service.RecursosMultimedia;

import com.escom.Creadordecasos.Dto.RecursoMultimediaDTO;
import com.escom.Creadordecasos.Entity.CasoEstudio;
import com.escom.Creadordecasos.Entity.RecursoMultimedia;
import com.escom.Creadordecasos.Exception.BadRequestException;
import com.escom.Creadordecasos.Mapper.RecursoMultimediaMapper;
import com.escom.Creadordecasos.Repository.CasoEstudioRepository;
import com.escom.Creadordecasos.Repository.RecursosMultimediaRepository;
import com.escom.Creadordecasos.Service.FilesManager.FilesManagerService;
import com.escom.Creadordecasos.Service.RecursosMultimedia.Bodies.RecursoMultimediaReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public ResponseEntity<List<RecursoMultimediaDTO>> getAllByListId(List<Long> list) {
        List<RecursoMultimedia> list_entity = recursosMultimediaRepository.findByIdIn(list);
        List<RecursoMultimediaDTO> list_dto = recursoMultimediaMapper.toListDto(list_entity);
        return ResponseEntity.ok(list_dto);
    }

    public ResponseEntity<RecursoMultimediaDTO> getById(Long id) {
        Optional<RecursoMultimedia> recursoMultimedia = recursosMultimediaRepository.findById(id);
        if (recursoMultimedia.isPresent()) {
            RecursoMultimediaDTO dto = recursoMultimediaMapper.toDto(recursoMultimedia.get());

            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<RecursoMultimediaDTO> create(
            Long usuario_id,
            String nombre,
            MultipartFile archivo_multimedia
    ) throws BadRequestException, IOException {

            if (!archivo_multimedia.isEmpty()) {
                RecursoMultimedia recursoMultimedia = RecursoMultimedia.builder()
                        .nombre(nombre)
                        .path_src(filesManagerService.saveMultimedia(archivo_multimedia, usuario_id,555555L))
                        .build();

                recursosMultimediaRepository.save(recursoMultimedia);

                RecursoMultimediaDTO dto = recursoMultimediaMapper.toDto(recursoMultimedia);

                return ResponseEntity.ok(dto);
            } else {
                throw new BadRequestException();
            }
    }


    public ResponseEntity<RecursoMultimediaDTO> update(
            Long id,
            Long usuario_id,
            String nombre,
            Long caso_estudio_id,
            MultipartFile archivo_multimedia
    ) {
        Optional<RecursoMultimedia> optionalRecursoMultimedia = recursosMultimediaRepository.findById(id);
        if (optionalRecursoMultimedia.isPresent()) {
            RecursoMultimedia recursoMultimedia = optionalRecursoMultimedia.get();
            Optional<CasoEstudio> optionalCasoEstudio = casoEstudioRepository.findById(caso_estudio_id);
            if (optionalCasoEstudio.isPresent()) {
                recursoMultimedia.setPath_src(recursoMultimedia.getPath_src());
                recursoMultimedia.setCaso_estudio(optionalCasoEstudio.get());
                recursoMultimedia.setNombre(nombre);

                try {
                    filesManagerService.updateMultimedia(recursoMultimedia.getPath_src(), archivo_multimedia);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                recursosMultimediaRepository.save(recursoMultimedia);
                RecursoMultimediaDTO dto = recursoMultimediaMapper.toDto(recursoMultimedia);

                return ResponseEntity.ok(dto);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Boolean> delete(Long id) {
        Optional<RecursoMultimedia> optionalRecursoMultimedia = recursosMultimediaRepository.findById(id);
        if (optionalRecursoMultimedia.isPresent()) {
            filesManagerService.deleteMultimedia(optionalRecursoMultimedia.get().getPath_src());
            recursosMultimediaRepository.deleteById(id);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }

    }

    public ResponseEntity<List<RecursoMultimediaDTO>> getMultimediasByIds(List<Long> multimediasIds) {
        List<RecursoMultimedia> list = recursosMultimediaRepository.findMultimediasByIds(multimediasIds);
        List<RecursoMultimediaDTO> list_dto = recursoMultimediaMapper.toListDto(list);
        return ResponseEntity.ok(list_dto);
    }
}
