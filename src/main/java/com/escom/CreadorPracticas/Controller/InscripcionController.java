package com.escom.CreadorPracticas.Controller;

import com.escom.CreadorPracticas.Dto.InscripcionDTO;
import com.escom.CreadorPracticas.Service.Inscripcion.Bodies.InscripcionReq;
import com.escom.CreadorPracticas.Service.Inscripcion.InscripcionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscripcion")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class InscripcionController {
    private final InscripcionService inscripcionService;

    @GetMapping("grupo/{grupoId}/estudiante/{username}")
    public ResponseEntity<InscripcionDTO> getById(@PathVariable String username, Long grupoId){
        return inscripcionService.getById(username,grupoId);
    }

    @GetMapping("getAllByEstudianteUsername/{username}")
    public ResponseEntity<List<InscripcionDTO>> getAllByEstudianteId(@PathVariable String username){
        return inscripcionService.getAllByEstudianteId(username);
    }

    @GetMapping("getAllByGrupoId/{id}")
    public ResponseEntity<List<InscripcionDTO>> getAllByEstudianteId(@PathVariable Long id){
        return inscripcionService.getAllByGrupoId(id);
    }

    @PostMapping()
    public ResponseEntity<InscripcionDTO> create(@RequestBody InscripcionReq inscripcionReq){
        return inscripcionService.create(inscripcionReq);
    }

    @PatchMapping()
    public ResponseEntity<Boolean> update(@RequestBody InscripcionReq inscripcionReq){
        return inscripcionService.update(inscripcionReq);
    }

    @DeleteMapping("grupo/{grupoId}/estudiante/{username}")
    public ResponseEntity<Boolean> delete(@PathVariable  String username, Long grupoId){
        return inscripcionService.delete(username,grupoId);
    }

}
