package com.escom.Creadordecasos.Controller;

import com.escom.Creadordecasos.Dto.EmailBody;
import com.escom.Creadordecasos.Service.Auth.*;
import com.escom.Creadordecasos.Service.Auth.Bodies.*;
import com.escom.Creadordecasos.Service.Email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
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
