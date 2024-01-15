package com.escom.Creadordecasos.Controller;


import com.escom.Creadordecasos.Dto.RecursoMultimediaDTO;
import com.escom.Creadordecasos.Exception.BadRequestException;
import com.escom.Creadordecasos.Service.RecursosMultimedia.Bodies.RecursoMultimediaReq;
import com.escom.Creadordecasos.Service.RecursosMultimedia.RecursoMultimediaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("{id}")
    public ResponseEntity<RecursoMultimediaDTO> getById(@PathVariable Long id) {
        return recursoMultimediaService.getById(id);
    }

    @PostMapping
    public ResponseEntity<RecursoMultimediaDTO> create(
            @RequestParam("usuario_id") Long usuario_id,
            @RequestParam("caso_estudio_id") Long caso_estudio_id,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("numero_orden") Integer numero_orden,
            @RequestParam("archivoMultimedia") MultipartFile archivoMultimedia
    ) {

        return recursoMultimediaService.create(
                usuario_id,
                caso_estudio_id,
                descripcion,
                numero_orden,
                archivoMultimedia
        );

    }

    @PatchMapping()
    public ResponseEntity<Boolean> update(
            @RequestParam("id") Long id,
            @RequestParam("usuario_id") Long usuario_id,
            @RequestParam("caso_estudio_id") Long caso_estudio_id,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("numero_orden") Integer numero_orden,
            @RequestParam("archivoMultimedia") MultipartFile archivoMultimedia
    ) {
        return recursoMultimediaService.update(
                id,
                usuario_id,
                caso_estudio_id,
                descripcion,
                numero_orden,
                archivoMultimedia
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return recursoMultimediaService.delete(id);
    }
}
