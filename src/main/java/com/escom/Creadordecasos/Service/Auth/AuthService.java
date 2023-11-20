package com.escom.Creadordecasos.Service.Auth;

import com.escom.Creadordecasos.Entity.Estudiante;
import com.escom.Creadordecasos.Entity.Profesor;
import com.escom.Creadordecasos.Entity.Usuario;
import com.escom.Creadordecasos.Repository.Usuarios.UsuarioRepository;
import com.escom.Creadordecasos.Security.JwtAuthenticationProvider;
import com.escom.Creadordecasos.Util.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<AuthResponse> registerAdmin(RegisterAdminRequest registerAdminRequest){

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
        }else {


            Date now = new Date();

            try {
                Usuario usuario = Usuario.builder()
                        .username(registerAdminRequest.getUsername())
                        .email(registerAdminRequest.getEmail())
                        .password_hash(passwordEncoder.encode(registerAdminRequest.getPassword()))
                        .nombre(registerAdminRequest.getNombre())
                        .apellido_paterno(registerAdminRequest.getApellido_paterno())
                        .apellido_materno(registerAdminRequest.getApellido_materno())
                        .fecha_nacimiento(now)
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
        }else {
            Date now = new Date();

            try {

                if (registerRequest.getRol().equals( Rol.TEACHER)){
                    Profesor profesor = Profesor.builder()
                            .username(registerRequest.getUsername())
                            .email(registerRequest.getEmail())
                            .password_hash(passwordEncoder.encode(registerRequest.getPassword()))
                            .nombre(registerRequest.getNombre())
                            .apellido_paterno(registerRequest.getApellido_paterno())
                            .apellido_materno(registerRequest.getApellido_materno())
                            .fecha_nacimiento(now)
                            .cedula(registerRequest.getCedula())
                            .escuela(registerRequest.getEscuela())
                            .grupos(new ArrayList<>())
                            .rol(Rol.TEACHER)
                            .build();
                    usuarioRepository.save(profesor);
                    return ResponseEntity.ok(AuthResponse.builder()
                            .jwt(jwtAuthenticationProvider.createToken(profesor))
                            .success(true)
                            .failureReason("Profesor Creado")
                            .build());

                } else if (registerRequest.getRol().equals( Rol.STUDENT)) {
                    Estudiante estudiante = Estudiante.builder()
                            .username(registerRequest.getUsername())
                            .email(registerRequest.getEmail())
                            .password_hash(passwordEncoder.encode(registerRequest.getPassword()))
                            .nombre(registerRequest.getNombre())
                            .apellido_paterno(registerRequest.getApellido_paterno())
                            .apellido_materno(registerRequest.getApellido_materno())
                            .fecha_nacimiento(now)
                            .boleta(registerRequest.getBoleta())
                            .semestre(registerRequest.getSemestre())
                            .inscripciones(new ArrayList<>())
                            .equipos(new ArrayList<>())
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
                        .failureReason("Error: " + e.toString())
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
        if(usuario.isPresent()){

            if (!passwordEncoder.matches(loginRequest.getPassword(), usuario.get().getPassword_hash())) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(AuthResponse.builder()
                                .jwt(null)
                                .success(false)
                                .failureReason("Contraseña o usuarios incorrectos")
                                .build());
            }else {
                return ResponseEntity.ok(AuthResponse.builder()
                        .jwt(jwtAuthenticationProvider.createToken(usuario.get()))
                        .success(true)
                        .failureReason("Iniciando sesion.")
                        .build());
            }
        }else{
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
