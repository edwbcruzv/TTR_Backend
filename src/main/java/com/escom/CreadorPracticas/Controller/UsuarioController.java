package com.escom.CreadorPracticas.Controller;

import com.escom.CreadorPracticas.Dto.UsuarioDTO;
import com.escom.CreadorPracticas.Service.Usuario.Bodies.UpdateUsuarioRequest;
import com.escom.CreadorPracticas.Service.Usuario.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("getAll")
    public ResponseEntity<List<UsuarioDTO>> getAll(){
        return usuarioService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioDTO> get(@PathVariable Long id){
        return usuarioService.get(id);
    }

    @PatchMapping("")
    public ResponseEntity<Boolean> update(@RequestBody UpdateUsuarioRequest updateUsuarioRequest){
        return usuarioService.update(updateUsuarioRequest);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable  Long id){
        return usuarioService.delete(id);
    }


}
