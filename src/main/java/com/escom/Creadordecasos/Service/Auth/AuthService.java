package com.escom.Creadordecasos.Service.Auth;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.escom.Creadordecasos.Dto.EmailBody;
import com.escom.Creadordecasos.Entity.Estudiante;
import com.escom.Creadordecasos.Entity.Profesor;
import com.escom.Creadordecasos.Entity.Usuario;
import com.escom.Creadordecasos.Exception.NotFoundException;
import com.escom.Creadordecasos.Repository.UsuarioRepository;
import com.escom.Creadordecasos.Security.JwtAuthenticationProvider;
import com.escom.Creadordecasos.Service.Auth.Bodies.*;
import com.escom.Creadordecasos.Service.Email.EmailService;
import com.escom.Creadordecasos.Util.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

/**
 * Servicio para la autenticación
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final EmailService emailService;

    public ResponseEntity<AuthResponse> recoverPassword(RecoverPasswordRequest req, String token) {
        AuthResponse res = new AuthResponse();
        res.setSuccess(false);
        try {
            Long id = jwtAuthenticationProvider.validateTemporalToken(token);
            Optional<Usuario> optional = usuarioRepository.findById(id);
            if (optional.isEmpty()) {
                throw new NotFoundException();
            }
            Usuario usuario = optional.get();
            usuario.setPasswordHash(passwordEncoder.encode(req.getPassword()));
            usuarioRepository.save(usuario);
            res.setSuccess(true);
        } catch (JWTDecodeException e) {
            res.setSuccess(false);
            res.setFailureReason("TOKEN INVALIDO");
        } catch (NotFoundException e) {
            res.setSuccess(false);
            res.setFailureReason("USUARIO NO ENCONTRADO");
        }


        return ResponseEntity.ok(res);
    }

    public ResponseEntity<Boolean> sendEmailPassword(String email) {
        // Se busca el usuario
        Optional<Usuario> optional = usuarioRepository.findByEmail(email);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = optional.get();

        // Se genera un token temporal
        String tokenTemporal = jwtAuthenticationProvider.generateTemporalToken(usuario.getUsername(), 600000);

        //  Se crea la ruta a donde se encuentra el formulario para crear una nueva contraseña
        String content = "http://localhost:3000/recover-password/" + tokenTemporal;

        EmailBody emailBody = EmailBody.builder()
                .email(email)
                .subject("Recuperacion de contraseña: CaseBuilder")
                .content(content)
                .build();

        emailService.sendEmail(emailBody);


        return ResponseEntity.ok(true);
    }

    public ResponseEntity<AuthResponse> registerAdmin(RegisterAdminRequest registerAdminRequest) {

        AuthResponse res;
        if (usuarioRepository.findByEmail(registerAdminRequest.getEmail()).isPresent()
                || usuarioRepository.findByUsername(registerAdminRequest.getUsername()).isPresent()) {
            Usuario usuario = usuarioRepository.getByUsername(registerAdminRequest.getUsername());

            res = AuthResponse.builder()
                    .jwt(jwtAuthenticationProvider.createToken(usuario))
                    .success(true)
                    .failureReason("El usuario ya esta registrado. Iniciando sesion.")
                    .build();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
        } else {


            LocalDate now = LocalDate.now();

            try {
                Usuario usuario = Usuario.builder()
                        .username(registerAdminRequest.getUsername())
                        .email(registerAdminRequest.getEmail())
                        .passwordHash(passwordEncoder.encode(registerAdminRequest.getPassword()))
                        .nombre(registerAdminRequest.getNombre())
                        .apellidoPaterno(registerAdminRequest.getApellidoPaterno())
                        .apellidoMaterno(registerAdminRequest.getApellidoMaterno())
                        .fechaNacimiento(now)
                        .rol(Rol.ADMIN)
                        .build();

                usuarioRepository.save(usuario);

                return ResponseEntity.ok(AuthResponse.builder()
                        .jwt(jwtAuthenticationProvider.createToken(usuario))
                        .success(true)
                        .failureReason("Usuario Creado")
                        .build());

            } catch (Exception e) {
                return ResponseEntity.ok(AuthResponse.builder()
                        .jwt(null)
                        .success(false)
                        .failureReason("Error: " + e.toString())
                        .build());
            }
        }
    }

    public ResponseEntity<AuthResponse> register(RegisterRequest registerRequest) {
        AuthResponse res;
        if (usuarioRepository.findByEmail(registerRequest.getEmail()).isPresent()
                || usuarioRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            Usuario usuario = usuarioRepository.getByUsername(registerRequest.getUsername());

            res = AuthResponse.builder()
                    .jwt(jwtAuthenticationProvider.createToken(usuario))
                    .success(true)
                    .failureReason("El usuario ya esta registrado. Iniciando sesion.")
                    .build();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
        } else {
            LocalDate now = LocalDate.now();

            try {

                if (registerRequest.getRol().equals(Rol.TEACHER)) {
                    Profesor profesor = Profesor.builder()
                            .username(registerRequest.getUsername())
                            .email(registerRequest.getEmail())
                            .passwordHash(passwordEncoder.encode(registerRequest.getPassword()))
                            .nombre(registerRequest.getNombre())
                            .apellidoMaterno(registerRequest.getApellidoPaterno())
                            .apellidoMaterno(registerRequest.getApellidoMaterno())
                            .fechaNacimiento(now)
                            .rol(Rol.TEACHER)
                            .build();
                    System.out.printf("Mueree?:"+profesor.getEmail());
                    usuarioRepository.save(profesor);

                    return ResponseEntity.ok(AuthResponse.builder()
                            .jwt(jwtAuthenticationProvider.createToken(profesor))
                            .success(true)
                            .failureReason("Profesor Creado")
                            .build());

                } else if (registerRequest.getRol().equals(Rol.STUDENT)) {
                    Estudiante estudiante = Estudiante.builder()
                            .username(registerRequest.getUsername())
                            .email(registerRequest.getEmail())
                            .passwordHash(passwordEncoder.encode(registerRequest.getPassword()))
                            .nombre(registerRequest.getNombre())
                            .apellidoPaterno(registerRequest.getApellidoPaterno())
                            .apellidoMaterno(registerRequest.getApellidoMaterno())
                            .fechaNacimiento(now)
                            .rol(Rol.STUDENT)
                            .build();
                    usuarioRepository.save(estudiante);

                    return ResponseEntity.ok(AuthResponse.builder()
                            .jwt(jwtAuthenticationProvider.createToken(estudiante))
                            .success(true)
                            .failureReason("Estudiante Creado")
                            .build());
                }


            } catch (Exception e) {
                return ResponseEntity.internalServerError().body(AuthResponse.builder()
                        .jwt(null)
                        .success(false)
                        .failureReason("Errorees: " + e.toString())
                        .build());
            }
        }
        return ResponseEntity.badRequest().body(AuthResponse.builder()
                .jwt(null)
                .success(false)
                .failureReason("Error: El Rol es invalido.")
                .build());
    }

    public ResponseEntity<AuthResponse> login(LoginRequest loginRequest) {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(loginRequest.getUsername());
        if (usuario.isPresent()) {

            if (!passwordEncoder.matches(loginRequest.getPassword(), usuario.get().getPasswordHash())) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(AuthResponse.builder()
                                .jwt(null)
                                .success(false)
                                .failureReason("Contraseña o usuarios incorrectos")
                                .build());
            } else {
                return ResponseEntity.ok(AuthResponse.builder()
                        .jwt(jwtAuthenticationProvider.createToken(usuario.get()))
                        .success(true)
                        .failureReason("Iniciando sesion.")
                        .build());
            }
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(AuthResponse.builder()
                            .jwt(null)
                            .success(false)
                            .failureReason("Error.")
                            .build());
        }

    }

}
