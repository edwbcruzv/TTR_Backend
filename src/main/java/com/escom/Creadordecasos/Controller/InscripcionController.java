package com.escom.Creadordecasos.Controller;


import com.escom.Creadordecasos.Dto.EquipoDTO;
import com.escom.Creadordecasos.Dto.InscripcionDTO;
import com.escom.Creadordecasos.Service.Inscripciones.Bodies.InscripcionReq;
import com.escom.Creadordecasos.Service.Inscripciones.InscripcionService;
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

    @GetMapping("{id}")
    public ResponseEntity<InscripcionDTO> getById(@PathVariable Long id){
        return inscripcionService.getById(id);
    }

    @GetMapping("getAllByEstudianteId/{id}")
    public ResponseEntity<List<InscripcionDTO>> getAllByEstudianteId(@PathVariable Long id){
        return inscripcionService.getAllByEstudianteId(id);
    }
    @PostMapping()
    public ResponseEntity<InscripcionDTO> create(@RequestBody InscripcionReq inscripcionReq){return inscripcionService.create(inscripcionReq);
    }
/*
    @PatchMapping()
    public ResponseEntity<Boolean> update(@RequestBody InscripcionReq inscripcionReq){
        return inscripcionService.update(inscripcionReq);
    }
*/
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable  Long id){
        return inscripcionService.delete(id);
    }

}
