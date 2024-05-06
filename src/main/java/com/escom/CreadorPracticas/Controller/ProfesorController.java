package com.escom.CreadorPracticas.Controller;

import com.escom.CreadorPracticas.Dto.ProfesorDTO;
import com.escom.CreadorPracticas.Service.Profesor.Bodies.UpdateProfesorRequest;
import com.escom.CreadorPracticas.Service.Profesor.ProfesorService;
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

    @GetMapping("getAll")
    public ResponseEntity<List<ProfesorDTO>> getAll(){
        return profesorService.getAll();
    }

    @GetMapping("{username}")
    public ResponseEntity<ProfesorDTO> get(@PathVariable String username){
        return profesorService.get(username);
    }

    @PatchMapping("")
    public ResponseEntity<Boolean> update(@RequestBody UpdateProfesorRequest updateProfesorRequest){
        return profesorService.update(updateProfesorRequest);
    }

    @DeleteMapping("{username}")
    public ResponseEntity<Boolean> delete(@PathVariable  String username){
        return profesorService.delete(username);
    }
}
