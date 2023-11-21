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
@RequestMapping("/estudiante")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class EstudianteController {
    private final EstudianteService estudianteService;

    @GetMapping()
    public ResponseEntity<List<EstudianteDTO>> getAll(){
        return estudianteService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<EstudianteDTO> get(@PathVariable Long id){
        return estudianteService.get(id);
    }

    @PatchMapping("")
    public ResponseEntity<Boolean> update(@RequestBody UpdateEstudianteRequest updateEstudianteRequest){
        return estudianteService.update(updateEstudianteRequest);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable  Long id){
        return estudianteService.delete(id);
    }
}
