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

    @GetMapping("{id}")
    public ResponseEntity<Resource> getById(@PathVariable Long id) {
        return recursoMultimediaService.getById(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RecursoMultimediaDTO> create(
            @RequestParam("username") String username,
            @RequestParam("nombre") String nombre,
            //@RequestParam("practicaId") Long practicaId,
            @RequestPart("archivoMultimedia") MultipartFile archivoMultimedia
    ) throws BadRequestException, IOException {
        return recursoMultimediaService.create(username,nombre,77777L,archivoMultimedia);
    }

    @PatchMapping()
    public ResponseEntity<RecursoMultimediaDTO> update(
            @RequestParam("username") String username,
            @RequestParam("id") Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("practicaId") Long practicaId,
            @RequestParam("archivoMultimedia") MultipartFile archivoMultimedia
    ) {
        return recursoMultimediaService.update(username,id,nombre,practicaId,archivoMultimedia);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return recursoMultimediaService.delete(id);
    }
}