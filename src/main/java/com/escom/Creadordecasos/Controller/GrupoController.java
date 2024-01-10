package com.escom.Creadordecasos.Controller;

import com.escom.Creadordecasos.Dto.GrupoDTO;
import com.escom.Creadordecasos.Service.Grupo.Bodies.GrupoReq;
import com.escom.Creadordecasos.Service.Grupo.GrupoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupo")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class GrupoController {
    private final GrupoService grupoService;

    @GetMapping("getAllByProfesorId/{id}")
    public ResponseEntity<List<GrupoDTO>> getAllByProfesorId(@PathVariable Long id){
        return grupoService.getAllByProfesorId(id);
    }
/*
    @GetMapping("getAllByProfesorId/{id}")
    public ResponseEntity<List<GrupoDTO>> getAllByProfesorId(@PathVariable Long id){
        return grupoService.getAllByProfesorId(id);
    }
*/
    @GetMapping("getAll")
    public ResponseEntity<List<GrupoDTO>> getAll(){
        return grupoService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<GrupoDTO> getById(@PathVariable Long id){
        return grupoService.getById(id);
    }

    @PostMapping()
    public ResponseEntity<GrupoDTO> create(@RequestBody GrupoReq grupoReq){
        return grupoService.create(grupoReq);
    }

    @PatchMapping()
    public ResponseEntity<Boolean> update(@RequestBody GrupoReq grupoReq){
        return grupoService.update(grupoReq);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable  Long id){
        return grupoService.delete(id);
    }

}
