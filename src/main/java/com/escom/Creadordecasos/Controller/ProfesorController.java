package com.escom.Creadordecasos.Controller;

import com.escom.Creadordecasos.Dto.ProfesorDTO;
import com.escom.Creadordecasos.Service.Profesores.Bodies.UpdateProfesorRequest;
import com.escom.Creadordecasos.Service.Profesores.ProfesorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/profesor")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class ProfesorController {
    private final ProfesorService profesorService;

    @GetMapping()
    public ResponseEntity<List<ProfesorDTO>> getAll(){
        return profesorService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfesorDTO> get(@PathVariable Long id){
        return profesorService.get(id);
    }

    @PatchMapping("")
    public ResponseEntity<Boolean> update(@RequestBody UpdateProfesorRequest updateProfesorRequest){
        return profesorService.update(updateProfesorRequest);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable  Long id){
        return profesorService.delete(id);
    }
}
