package com.escom.Creadordecasos.Controller;


import com.escom.Creadordecasos.Dto.RecursoMultimediaDTO;
import com.escom.Creadordecasos.Exception.BadRequestException;
import com.escom.Creadordecasos.Service.RecursosMultimedia.Bodies.RecursoMultimediaReqListIds;
import com.escom.Creadordecasos.Service.RecursosMultimedia.RecursoMultimediaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<RecursoMultimediaDTO>> getAllByListId(@RequestBody List<Long> list) {
        return recursoMultimediaService.getAllByListId(list);
    }
    @PostMapping("getMultimediasByIds")
    public ResponseEntity<List<RecursoMultimediaDTO>> getMultimediasByIds(@RequestBody RecursoMultimediaReqListIds recursoMultimediaReqListIds) {
        return recursoMultimediaService.getMultimediasByIds(recursoMultimediaReqListIds.getMultimedias_ids());
    }

    @GetMapping("{id}")
    public ResponseEntity<RecursoMultimediaDTO> getById(@PathVariable Long id) {
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
            @RequestParam("id") Long id,
            @RequestParam("usuario_id") Long usuario_id,
            @RequestParam("caso_estudio_id") Long caso_estudio_id,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("archivo_multimedia") MultipartFile archivo_multimedia
    ) {
        return recursoMultimediaService.update(
                id,
                usuario_id,
                descripcion,
                caso_estudio_id,
                archivo_multimedia
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return recursoMultimediaService.delete(id);
    }
}
