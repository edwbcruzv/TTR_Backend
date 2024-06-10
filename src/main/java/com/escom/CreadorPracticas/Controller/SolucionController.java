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

    @GetMapping("getAllEquipo")
    public ResponseEntity<List<SolucionMinDTO>>  getAllEquipo(){
        return solucionService.getAllEquipo();
    }

    @GetMapping("getAllIndividual")
    public ResponseEntity<List<SolucionMinDTO>>  getAllIndividual(){
        return solucionService.getAllIndividual();
    }

    @GetMapping("getAllEquipoByEquipoId/{equipoId}")
    public ResponseEntity<List<SolucionMinDTO>>  getAllEquipoByEquipoId(@PathVariable Long equipoId){
        return solucionService.getAllEquipoByEquipoId(equipoId);
    }

    @GetMapping("getAllIndividualByEstudianteUsername/{username}")
    public ResponseEntity<List<SolucionMinDTO>>  getAllIndividualByEstudianteUsername(@PathVariable String username){
        return solucionService.getAllIndividualByEstudianteUsername(username);
    }

    @GetMapping("getAllIndividualByGrupoId/{grupoId}")
    public ResponseEntity<List<SolucionMinDTO>>  getAllIndividualByGrupoId(@PathVariable Long grupoId){
        return solucionService.getAllIndividualByGrupoId(grupoId);
    }

    @PatchMapping()
    public ResponseEntity<Boolean> update(@RequestBody SolucionReq solucionReq){
        return solucionService.update(solucionReq);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable  Long id){
        return solucionService.delete(id);
    }
}
