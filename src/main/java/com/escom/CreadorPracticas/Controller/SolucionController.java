package com.escom.CreadorPracticas.Controller;

import com.escom.CreadorPracticas.Dto.SolucionDTO;
import com.escom.CreadorPracticas.Service.Solucion.Bodies.SolucionReq;
import com.escom.CreadorPracticas.Service.Solucion.SolucionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solucion")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Getter
@Setter
public class SolucionController {

    private SolucionService solucionService;

    @PostMapping
    public ResponseEntity<SolucionDTO> create(@RequestBody SolucionReq req) {
        return solucionService.create(req);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<SolucionDTO>> getAll() {
        return  solucionService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolucionDTO> getById(@PathVariable Long id) {
        return solucionService.getById(id);
    }

    @PutMapping
    public ResponseEntity<SolucionDTO> update(@RequestBody SolucionReq req) {
        return solucionService.update(req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return solucionService.delete(id);
    }
}
