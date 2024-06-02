package com.escom.CreadorPracticas.Controller;

import com.escom.CreadorPracticas.Dto.PracticaDTO;
import com.escom.CreadorPracticas.Dto.SolucionDTO;
import com.escom.CreadorPracticas.Dto.SolucionMinDTO;
import com.escom.CreadorPracticas.Service.Practica.Bodies.PracticaReq;
import com.escom.CreadorPracticas.Service.Practica.PracticaService;
import com.escom.CreadorPracticas.Service.Solucion.Bodies.SolucionReq;
import com.escom.CreadorPracticas.Service.Solucion.SolucionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solucion")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class SolucionController {
    private final SolucionService solucionService;
    @GetMapping("{id}")
    public ResponseEntity<SolucionDTO> get(@PathVariable Long id){
        return solucionService.get(id);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<SolucionMinDTO>>  getAll(){
        return solucionService.getAll();
    }

    @GetMapping("getAllByEquipoId/{id}")
    public ResponseEntity<List<SolucionMinDTO>>  getAllByEquipoId(@PathVariable Long id){
        return solucionService.getAllByEquipoId(id);
    }

    @GetMapping("getAllByEstudianteUsername/{username}")
    public ResponseEntity<List<SolucionMinDTO>>  getAllByEstudianteUsername(@PathVariable String username){
        return solucionService.getAllByEstudianteUsername(username);
    }

    @GetMapping("getAllByProfesorUsernameAndGrupoIdByEquipos/{profesorUsername}/{grupoId}")
    public ResponseEntity<List<SolucionMinDTO>>  getAllByProfesorUsernameAndGrupoIdByEquipos(@PathVariable String profesorUsername, Long grupoId){
        return solucionService.getAllByProfesorUsernameAndGrupoIdByEquipos(profesorUsername,grupoId);
    }

    @GetMapping("getAllByProfesorUsernameAndGrupoIdByIndividual/{profesorUsername}/{grupoId}")
    public ResponseEntity<List<SolucionMinDTO>>  getAllByProfesorUsernameAndGrupoIdByIndividual(@PathVariable String profesorUsername, Long grupoId){
        return solucionService.getAllByProfesorUsernameAndGrupoIdByIndividual(profesorUsername,grupoId);
    }

    /*
    @PostMapping()
    public ResponseEntity<SolucionDTO> create(@RequestBody SolucionReq solucionReq){
        return solucionService.create(solucionReq);
    }
     */

    @PatchMapping()
    public ResponseEntity<Boolean> update(@RequestBody SolucionReq solucionReq){
        return solucionService.update(solucionReq);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable  Long id){
        return solucionService.delete(id);
    }
}
