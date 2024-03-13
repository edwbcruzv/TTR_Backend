package com.escom.CreadorPracticas.Service.Auth;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.escom.CreadorPracticas.Dto.EmailBody;
import com.escom.CreadorPracticas.Entity.Estudiante;
import com.escom.CreadorPracticas.Entity.Profesor;
import com.escom.CreadorPracticas.Entity.Usuario;
import com.escom.CreadorPracticas.Exception.NotFoundException;
import com.escom.CreadorPracticas.Repository.UsuarioRepository;
import com.escom.CreadorPracticas.Security.JwtAuthenticationProvider;
import com.escom.CreadorPracticas.Service.Auth.Bodies.*;
import com.escom.CreadorPracticas.Service.Email.EmailService;
import com.escom.CreadorPracticas.Util.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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


    public ResponseEntity<AuthResponse> registerTeacher(RegisterTeacherRequest req) {
        AuthResponse res;
        if (usuarioRepository.findByEmail(req.getEmail()).isPresent()
                || usuarioRepository.findByUsername(req.getUsername()).isPresent()) {
            Usuario usuario = usuarioRepository.getByUsername(req.getUsername());

            res = AuthResponse.builder()
                    .jwt(jwtAuthenticationProvider.createToken(usuario))
                    .success(true)
                    .failureReason("El usuario ya esta registrado. Iniciando sesion.")
                    .build();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
        } else {
            LocalDate now = LocalDate.now();

            try {

                if (req.getRol().equals(Rol.TEACHER)) {
                    Profesor profesor = Profesor.builder()
                            .username(req.getUsername())
                            .email(req.getEmail())
                            .passwordHash(passwordEncoder.encode(req.getPassword()))
                            .nombre(req.getNombre())
                            .apellidoPaterno(req.getApellidoPaterno())
                            .apellidoMaterno(req.getApellidoMaterno())
                            .fechaNacimiento(now)
                            .cedula(req.getCedula())
                            .rol(Rol.TEACHER)
                            .build();
                    usuarioRepository.save(profesor);

                    return ResponseEntity.ok(AuthResponse.builder()
                            .jwt(jwtAuthenticationProvider.createToken(profesor))
                            .success(true)
                            .failureReason("Profesor Creado")
                            .build());

                }
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body(AuthResponse.builder()
                        .jwt(null)
                        .success(false)
                        .failureReason("Error al registar al profesor: " + e.toString())
                        .build());
            }
        }
        return ResponseEntity.badRequest().body(AuthResponse.builder()
                .jwt(null)
                .success(false)
                .failureReason("Error: El Rol es invalido.")
                .build());
    }


    public ResponseEntity<AuthResponse> registerStudent(RegisterStudentRequest req) {
        AuthResponse res;
        if (usuarioRepository.findByEmail(req.getEmail()).isPresent()
                || usuarioRepository.findByUsername(req.getUsername()).isPresent()) {
            Usuario usuario = usuarioRepository.getByUsername(req.getUsername());

            res = AuthResponse.builder()
                    .jwt(jwtAuthenticationProvider.createToken(usuario))
                    .success(true)
                    .failureReason("El usuario ya esta registrado. Iniciando sesion.")
                    .build();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
        } else {
            LocalDate now = LocalDate.now();

            try {

                if (req.getRol().equals(Rol.STUDENT)) {
                    Estudiante estudiante = Estudiante.builder()
                            .username(req.getUsername())
                            .email(req.getEmail())
                            .passwordHash(passwordEncoder.encode(req.getPassword()))
                            .nombre(req.getNombre())
                            .apellidoPaterno(req.getApellidoPaterno())
                            .apellidoMaterno(req.getApellidoMaterno())
                            .fechaNacimiento(now)
                            .boleta(req.getBoleta())
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
                        .failureReason("Error al registrar al estudiante: " + e.toString())
                        .build());
            }
        }
        return ResponseEntity.badRequest().body(AuthResponse.builder()
                .jwt(null)
                .success(false)
                .failureReason("Error: El Rol es invalido.")
                .build());
    }

}
