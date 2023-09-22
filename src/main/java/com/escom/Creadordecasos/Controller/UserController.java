package com.escom.Creadordecasos.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para los usuarios en general
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Test de usuarios
     * @return
     */
    @GetMapping("/test")
    public ResponseEntity test() {
        return ResponseEntity.ok().build();
    }

}
