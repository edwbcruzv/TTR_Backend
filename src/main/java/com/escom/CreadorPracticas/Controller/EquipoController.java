package com.escom.CreadorPracticas.Controller;

import com.escom.CreadorPracticas.Dto.EquipoDTO;
import com.escom.CreadorPracticas.Service.Equipo.Bodies.EquipoReq;
import com.escom.CreadorPracticas.Service.Equipo.EquipoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipo")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class EquipoController {
    private final EquipoService equipoService;

    @GetMapping("getAllByGrupoId/{id}")
    public ResponseEntity<List<EquipoDTO>> getAllByGrupoId(@PathVariable Long id){
        return equipoService.getAllByGrupoId(id);
    }
    @GetMapping("{id}")
    public ResponseEntity<EquipoDTO> getById(@PathVariable Long id){
        return equipoService.getById(id);
    }

    @PostMapping()
    public ResponseEntity<EquipoDTO> create(@RequestBody EquipoReq equipoReq){return equipoService.create(equipoReq);
    }

    @PatchMapping()
    public ResponseEntity<Boolean> update(@RequestBody EquipoReq equipoReq){
        return equipoService.update(equipoReq);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable  Long id){
        return equipoService.delete(id);
    }

}
