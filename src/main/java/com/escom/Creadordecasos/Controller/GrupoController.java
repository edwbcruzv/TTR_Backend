package com.escom.Creadordecasos.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public class GrupoController implements Serializable {
    private final GrupoService grupoService;

    @GetMapping()
    public ResponseEntity<List<GrupoDTO>> getAll(){
        return grupoService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<GrupoDTO> get(@PathVariable Long id){
        return grupoService.get(id);
    }

    @PatchMapping("")
    public ResponseEntity<Boolean> update(@RequestBody UpdateGrupooRequest updateGrupoRequest){
        return grupoService.update(updateGrupoRequest);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable  Long id){
        return grupoService.delete(id);
    }
}
