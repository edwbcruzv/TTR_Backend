package com.escom.CreadorPracticas.Controller;

import com.escom.CreadorPracticas.Dto.EstudianteDTO;
import com.escom.CreadorPracticas.Service.Estudiante.Bodies.EstudianteListUsernames;
import com.escom.CreadorPracticas.Service.Estudiante.Bodies.UpdateEstudianteRequest;
import com.escom.CreadorPracticas.Service.Estudiante.EstudianteService;
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

    @GetMapping("getAll")
    public ResponseEntity<List<EstudianteDTO>> getAll(){
        return estudianteService.getAll();
    }

    @GetMapping("getAllByGroupId/{id}")
    public ResponseEntity<List<EstudianteDTO>> getAllByGroupId(@PathVariable Long id){
        return estudianteService.getAllByGroupId(id);
    }
/*
    @GetMapping("getAllByGroupId/{id}/NotTeam")
    public ResponseEntity<List<EstudianteDTO>> getAllByGroupIdAndNotTeam(@PathVariable Long id){
        return estudianteService.getAllByGroupIdAndNotTeam(id);
    }
*/
    @GetMapping("{username}")
    public ResponseEntity<EstudianteDTO> get(@PathVariable String username){
        return estudianteService.get(username);
    }

    @PostMapping("getByUsernames")
    public ResponseEntity<List<EstudianteDTO>> getByUsernames(@RequestBody EstudianteListUsernames listUsernames) {
        return estudianteService.getEstudiantesByIds(listUsernames.getEstudiantesUsernames());
    }

    @PatchMapping("")
    public ResponseEntity<Boolean> update(@RequestBody UpdateEstudianteRequest updateEstudianteRequest){
        return estudianteService.update(updateEstudianteRequest);
    }

    @DeleteMapping("{username}")
    public ResponseEntity<Boolean> delete(@PathVariable  String username){
        return estudianteService.delete(username);
    }

}
