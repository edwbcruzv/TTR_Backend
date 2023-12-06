package com.escom.Creadordecasos.Controller;

import com.escom.Creadordecasos.Dto.CasoEstudioDTO;
import com.escom.Creadordecasos.Entity.CasoEstudio;
import com.escom.Creadordecasos.Exception.BadRequestException;
import com.escom.Creadordecasos.Exception.NotFoundException;
import com.escom.Creadordecasos.Service.CasosEstudio.Bodies.CasoEstudioReq;
import com.escom.Creadordecasos.Service.CasosEstudio.CasoEstudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("CasoEstudio")
@RequiredArgsConstructor
public class CasoEstudioController {
    private final CasoEstudioService casoEstudioService;

    // CREATE
    @PostMapping
    public ResponseEntity<CasoEstudioDTO> crear(@RequestBody CasoEstudioReq casoEstudioReq) {
        try {
            CasoEstudioDTO data = casoEstudioService.crear(casoEstudioReq);
            return ResponseEntity.ok(data);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // READ
    @GetMapping
    public ResponseEntity<List<CasoEstudioDTO>> obtenerTodos() {
        List<CasoEstudioDTO> data = casoEstudioService.obtenerTodos();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CasoEstudioDTO> obtenerPorId(@PathVariable Long id) {
        try {
            CasoEstudioDTO data = casoEstudioService.obtenerPorId(id);
            return ResponseEntity.ok(data);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // UPDATE
    @PatchMapping
    public ResponseEntity<CasoEstudioDTO> actualizar(@RequestBody CasoEstudioReq casoEstudio) {
        try {
            CasoEstudioDTO data = casoEstudioService.actualizar(casoEstudio);
            return ResponseEntity.ok(data);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable Long id) {
        try {
            Boolean data = casoEstudioService.eliminar(id);
            return ResponseEntity.ok(data);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

