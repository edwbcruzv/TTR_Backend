package com.escom.Creadordecasos.Controller;


import com.escom.Creadordecasos.Dto.EstudianteDTO;
import com.escom.Creadordecasos.Service.Estudiantes.Bodies.UpdateEstudianteRequest;
import com.escom.Creadordecasos.Service.Estudiantes.EstudianteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupo")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class EquipoController {
    private final EquipoService equipoService;

    @GetMapping()
    public ResponseEntity<List<EquipoDTO>> getAll(){
        return EquipoService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<EquipoDTO> get(@PathVariable Long id){
        return equipoService.get(id);
    }

    @PatchMapping("")
    public ResponseEntity<Boolean> update(@RequestBody UpdateEquipoRequest updateEquipoRequest){
        return equipoService.update(updateEquipoRequest);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable  Long id){
        return equipoService.delete(id);
    }

}
