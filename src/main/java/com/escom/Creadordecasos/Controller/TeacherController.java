package com.escom.Creadordecasos.Controller;

import com.escom.Creadordecasos.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    /**
     * Servicio de usuarios
     */
    private final UserService userService;

    /**
     * Test para probar el rol de maestro
     * @return
     */
    @GetMapping("/test")
    public ResponseEntity testTeacher() {
        return ResponseEntity.ok().build();
    }
}
