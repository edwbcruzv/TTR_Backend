package com.escom.Creadordecasos.Controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipo")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class EquipoController {
/*
    @GetMapping("{id}")
    public ResponseEntity<RecursoMultimediaDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(null);
    }

    @PostMapping()
    public ResponseEntity<RecursoMultimediaDTO> create(@RequestBody RecursoMultimediaReq recursoMultimediaReq){
        return ResponseEntity.ok(null);
    }

    @PatchMapping()
    public ResponseEntity<Boolean> update(@RequestBody RecursoMultimediaReq recursoMultimediaReq){
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable  Long id){
        return ResponseEntity.ok(null);
    }*/
}
