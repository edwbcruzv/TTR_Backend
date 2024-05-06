package com.escom.CreadorPracticas.Controller;

import com.escom.CreadorPracticas.Dto.RecursoMultimediaDTO;
import com.escom.CreadorPracticas.Exception.BadRequestException;
import com.escom.CreadorPracticas.Service.RecursoMultimedia.Bodies.RecursoMultimediaReqListIds;
import com.escom.CreadorPracticas.Service.RecursoMultimedia.RecursoMultimediaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/multimedia")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class RecursoMultimediaController {
    private final RecursoMultimediaService recursoMultimediaService;

    @PostMapping("/getAllByListId")
    public ResponseEntity<List<RecursoMultimediaDTO>> getAllByListId(@RequestBody RecursoMultimediaReqListIds recursoMultimediaReqListIds) {
        return recursoMultimediaService.getAllByListId(recursoMultimediaReqListIds.getMultimediaIds());
    }
    /*
    @PostMapping("getMultimediasByIds")
    public ResponseEntity<List<Resource>> getMultimediasByIds(@RequestBody RecursoMultimediaReqListIds recursoMultimediaReqListIds) {
        return recursoMultimediaService.getMultimediasByIds(recursoMultimediaReqListIds.getMultimedias_ids());
    }*/

    @GetMapping("{id}")
    public ResponseEntity<Resource> getById(@PathVariable Long id) {
        return recursoMultimediaService.getById(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RecursoMultimediaDTO> create(
            @RequestParam("usuario_id") Long usuario_id,
            @RequestParam("nombre") String nombre,
            @RequestPart("archivo_multimedia") MultipartFile archivo_multimedia
    ) throws BadRequestException, IOException {
        return recursoMultimediaService.create(
                usuario_id,
                nombre,
                archivo_multimedia
        );
    }

    @PatchMapping()
    public ResponseEntity<RecursoMultimediaDTO> update(
            @RequestParam("username") String username,
            @RequestParam("id") Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("practicaId") Long practicaId,
            @RequestParam("fileMultimedia") MultipartFile fileMultimedia
    ) {
        return recursoMultimediaService.update(
                username,
                id,
                nombre,
                practicaId,
                fileMultimedia
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RecursoMultimediaDTO> delete(@PathVariable Long id) {
        return recursoMultimediaService.delete(id);
    }
}