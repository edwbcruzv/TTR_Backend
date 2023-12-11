package com.escom.Creadordecasos.Controller;


import com.escom.Creadordecasos.Dto.RecursoMultimediaDTO;
import com.escom.Creadordecasos.Service.RecursosMultimedia.Bodies.RecursoMultimediaReq;
import com.escom.Creadordecasos.Service.RecursosMultimedia.RecursoMultimediaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/multimedia")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class RecursoMultimediaController {
    private final RecursoMultimediaService recursoMultimediaService;

    @PostMapping("/getAllByListId")
    public ResponseEntity<List<RecursoMultimediaDTO>> getAllByListId(@RequestBody List<Long> list){
        return recursoMultimediaService.getAllByListId(list);
    }
    @GetMapping("{id}")
    public ResponseEntity<RecursoMultimediaDTO> getById(@PathVariable  Long id){
        return recursoMultimediaService.getById(id);
    }

    @PostMapping()
    public ResponseEntity<RecursoMultimediaDTO> create(@RequestBody RecursoMultimediaReq recursoMultimediaReq){
        return recursoMultimediaService.create(recursoMultimediaReq);
    }

    @PatchMapping()
    public ResponseEntity<Boolean> update(@RequestBody RecursoMultimediaReq recursoMultimediaReq){
        return recursoMultimediaService.update(recursoMultimediaReq);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable  Long id){
        return recursoMultimediaService.delete(id);
    }
}
