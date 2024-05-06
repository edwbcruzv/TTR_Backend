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

    @GetMapping("{username}")
    public ResponseEntity<UsuarioDTO> get(@PathVariable String username){
        return usuarioService.get(username);
    }

    @PatchMapping("")
    public ResponseEntity<Boolean> update(@RequestBody UpdateUsuarioRequest updateUsuarioRequest){
        return usuarioService.update(updateUsuarioRequest);
    }

    @DeleteMapping("{username}")
    public ResponseEntity<Boolean> delete(@PathVariable  String username){
        return usuarioService.delete(username);
    }


}
