package com.escom.CreadorPracticas.Controller;

import com.escom.CreadorPracticas.Dto.GrupoDTO;
import com.escom.CreadorPracticas.Service.Grupo.Bodies.GrupoReq;
import com.escom.CreadorPracticas.Service.Grupo.GrupoService;
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

    @GetMapping("getAllByProfesorUsername/{username}")
    public ResponseEntity<List<GrupoDTO>> getAllByProfesorUsername(@PathVariable String username){
        return grupoService.getAllByProfesorUsername(username);
    }
/*
    @GetMapping("getAll")
    public ResponseEntity<List<GrupoDTO>> getAll(){
        return grupoService.getAll();
    }
    */

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
