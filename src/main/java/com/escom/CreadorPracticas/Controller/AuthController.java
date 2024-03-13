package com.escom.CreadorPracticas.Controller;

import com.escom.CreadorPracticas.Service.Auth.*;
import com.escom.CreadorPracticas.Service.Auth.Bodies.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para los administradores
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
//@SecurityRequirement(name = "Bearer Authentication")
public class AuthController {

    /**
     * Servicio de usuario
     */
    private final AuthService authService;

    @PostMapping("/register-admin")
    public ResponseEntity<AuthResponse> registerAdmin(@RequestBody RegisterAdminRequest registerAdminRequest) {
        return authService.registerAdmin(registerAdminRequest);
    }

    @PostMapping("/register-teacher")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterTeacherRequest req) {
        return authService.registerTeacher(req);
    }

    @PostMapping("/register-student")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterStudentRequest req) {
        return authService.registerStudent(req);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/email-password")
    public ResponseEntity<Boolean> sendEmailPassword(@RequestBody EmailPasswordRequest req) {
        return authService.sendEmailPassword(req.getEmail());
    }

    @PostMapping("/recover-password/{token}")
    public ResponseEntity<AuthResponse> recoverPassword(@RequestBody RecoverPasswordRequest req, @PathVariable String token) {
        return authService.recoverPassword(req, token);
    }

}
