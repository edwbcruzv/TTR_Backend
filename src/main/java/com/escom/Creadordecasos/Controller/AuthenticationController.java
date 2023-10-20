package com.escom.Creadordecasos.Controller;

import com.escom.Creadordecasos.Controller.Bodies.LoginBody;
import com.escom.Creadordecasos.Controller.Bodies.RegistrationBody;
import com.escom.Creadordecasos.Exception.UserAlreadyExistsException;
import com.escom.Creadordecasos.Exception.UserNotFoundException;
import com.escom.Creadordecasos.Exception.WrongPasswordException;
import com.escom.Creadordecasos.Service.AuthService;
import com.escom.Creadordecasos.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para las autenticaciones
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private AuthService authService;

    private UserService userService;

    /**
     * Registro de usuario de manera publica por lo que debe borrarse
     *
     * @param registrationBody
     * @return
     */
/*
    @PostMapping("/register-admin")
    public ResponseEntity registerAdmin(@Valid @RequestBody RegistrationBody registrationBody) {
        try {
            authService.registerAdmin(registrationBody);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Registra un nuevo estudiante en el sistema
     *
     * @param registrationBody Estructura que contiene al estudiante a crear
     * @return Http status CREATED si se registro correctamente,
     * http status CONFLICT si las credenciales ya estan registradas
     */
    /*
    @PostMapping("/register-student")
    public ResponseEntity registerStudent(@Valid @RequestBody
                                          RegistrationBody registrationBody) {
        try {
            authService.registerStudent(registrationBody);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Registra un nuevo maestro en el sistema
     *
     * @param registrationBody Estructura que contiene al estudiante a crear
     * @return Http status CREATED si se registro correctamente,
     * http status CONFLICT si las credenciales ya estan registradas
     */
    /*
    @PostMapping("/register-teacher")
    public ResponseEntity registerTeacher(@Valid @RequestBody
                                          RegistrationBody registrationBody) {
        try {
            authService.registerTeacher(registrationBody);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Autentica y devuelve un JWT
     *
     * @param loginBody Estructura para el login
     * @return LoginResponse con el JWT creado si se autentico correctamente, LoginResponse con la razón del error sino
     */

    /*
    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody
                                               LoginBody loginBody) {
        String jwt = null;
        LoginResponse response = new LoginResponse();

        try {
            jwt = authService.loginUser(loginBody);
            response.setJwt(jwt);
            response.setSuccess(true);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            response.setSuccess(false);
            response.setFailureReason("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(response);
        } catch (WrongPasswordException e) {
            response.setSuccess(false);
            response.setFailureReason("Wrong password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }*/
}