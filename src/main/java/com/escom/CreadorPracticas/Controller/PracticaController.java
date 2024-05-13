package com.escom.CreadorPracticas.Controller;

import com.escom.CreadorPracticas.Dto.PracticaDTO;
import com.escom.CreadorPracticas.Service.Practica.Bodies.PracticaReq;
import com.escom.CreadorPracticas.Service.Practica.PracticaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/practica")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Getter
@Setter
public class PracticaController {
    private PracticaService practicaService;

    @PostMapping
    public ResponseEntity<PracticaDTO> create(@RequestBody PracticaReq req) {
        return practicaService.create(req);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PracticaDTO>> getAll() {
        return practicaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PracticaDTO> getById(@PathVariable Long id) {
        return practicaService.getById(id);
    }

    @PutMapping
    public ResponseEntity<PracticaDTO> update(@RequestBody PracticaReq req) {
        return practicaService.update(req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return practicaService.delete(id);
    }
}
