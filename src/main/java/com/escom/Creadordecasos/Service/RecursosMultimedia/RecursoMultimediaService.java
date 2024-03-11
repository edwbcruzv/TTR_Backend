package com.escom.Creadordecasos.Service.RecursosMultimedia;

import com.escom.Creadordecasos.Dto.RecursoMultimediaDTO;
import com.escom.Creadordecasos.Entity.RecursoMultimedia;
import com.escom.Creadordecasos.Exception.BadRequestException;
import com.escom.Creadordecasos.Mapper.RecursoMultimediaMapper;
import com.escom.Creadordecasos.Repository.RecursosMultimediaRepository;
import com.escom.Creadordecasos.Service.FilesManager.FilesManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecursoMultimediaService {

    private final RecursosMultimediaRepository recursosMultimediaRepository;
    private final FilesManagerService filesManagerService;
    private final RecursoMultimediaMapper recursoMultimediaMapper;

    public ResponseEntity<List<RecursoMultimediaDTO>> getAllByListId(List<Long> list) {
        List<RecursoMultimedia> list_entity = recursosMultimediaRepository.findByIdIn(list);
        List<RecursoMultimediaDTO> list_dto = recursoMultimediaMapper.toListDto(list_entity);
        return ResponseEntity.ok(list_dto);
    }

    public ResponseEntity<Resource> getById(Long id) {
        Optional<RecursoMultimedia> recursoMultimedia = recursosMultimediaRepository.findById(id);

        if (recursoMultimedia.isPresent()) {
            File file = new File(recursoMultimedia.get().getSrcFile());
            FileSystemResource resource = new FileSystemResource(file);

            if (resource.exists()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("id", String.valueOf(id));
                headers.add("name", file.getName());
                // Obtén el tipo de contenido basado en la extensión del archivo
                String contentType = determineContentType(file.getName());
                headers.setContentType(MediaType.parseMediaType(contentType));

                // Configura el encabezado para la descarga del archivo
                headers.setContentDispositionFormData("attachment", file.getName());

                return new ResponseEntity<>(resource, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Función para determinar el tipo de contenido basado en la extensión del archivo
    private String determineContentType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

        switch (fileExtension) {
            case "jpg":
            case "jpeg":
            case "png":
            case "gif":
            case "bmp":
            case "webp":
                return "image/" + fileExtension;
            case "mpeg":
            case "wav":
            case "ogg":
            case "midi":
                return "audio/" + fileExtension;
            case "mp4":
            case "webm":
            case "quicktime":
                return "video/" + fileExtension;
            case "pdf":
            case "msword":
            case "vnd.openxmlformats-officedocument.wordprocessingml.document":
            case "ms-excel":
            case "vnd.openxmlformats-officedocument.spreadsheetml.sheet":
            case "ms-powerpoint":
            case "vnd.openxmlformats-officedocument.presentationml.presentation":
                return "application/" + fileExtension;
            default:
                return "application/octet-stream"; // Tipo de contenido genérico si no se encuentra coincidencia
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
                        .srcFile(filesManagerService.saveMultimedia(archivo_multimedia, usuario_id,555555L))
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
            /*
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
            */

            return ResponseEntity.badRequest().body(null);

        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<RecursoMultimediaDTO> delete(Long id) {
        Optional<RecursoMultimedia> optionalRecursoMultimedia = recursosMultimediaRepository.findById(id);

        if (optionalRecursoMultimedia.isPresent()) {
            RecursoMultimedia entity = optionalRecursoMultimedia.get();

            //casoEstudioService.eliminarRecursoMultimedia(entity.getCaso_estudio().getId(),id);

            RecursoMultimediaDTO dto = recursoMultimediaMapper.toDto(entity);
            filesManagerService.deleteMultimedia(optionalRecursoMultimedia.get().getSrcFile());
            recursosMultimediaRepository.deleteById(id);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.ok(null);
        }

    }

}
