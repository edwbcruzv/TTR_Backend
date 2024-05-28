package com.escom.CreadorPracticas.Controller;

import com.escom.CreadorPracticas.Dto.PracticaDTO;
import com.escom.CreadorPracticas.Service.Practica.Bodies.PracticaAsignarReq;
import com.escom.CreadorPracticas.Service.Practica.Bodies.PracticaReq;
import com.escom.CreadorPracticas.Service.Practica.PracticaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/practica")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class PracticaController {
    private final PracticaService practicaService;

    @GetMapping("{id}")
    public ResponseEntity<PracticaDTO> get(@PathVariable Long id){
        return practicaService.get(id);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<PracticaDTO>>  getAll(){
        return practicaService.getAll();
    }

    @GetMapping("getAllByProfesorUsername/{username}")
    public ResponseEntity<List<PracticaDTO>> getAllByProfesorUsername(@PathVariable String username){
        return practicaService.getAllByProfesorUsername(username);
    }

    @PostMapping()
    public ResponseEntity<PracticaDTO> create(@RequestBody PracticaReq practicaReq){
        return practicaService.create(practicaReq);
    }

    @PostMapping("asignar")
    public ResponseEntity<Boolean> asignar(@RequestBody PracticaAsignarReq practicaAsignarReq){
        return practicaService.asignar(practicaAsignarReq);
    }

    @PatchMapping()
    public ResponseEntity<Boolean> update(@RequestBody PracticaReq practicaReq){
        return practicaService.update(practicaReq);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable  Long id){
        return practicaService.delete(id);
    }
}
