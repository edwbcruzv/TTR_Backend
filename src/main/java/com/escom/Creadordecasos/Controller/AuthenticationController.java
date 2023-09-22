package com.escom.Creadordecasos.Controller;

import com.escom.Creadordecasos.Dto.LoginBody;
import com.escom.Creadordecasos.Dto.LoginResponse;
import com.escom.Creadordecasos.Dto.RegistrationBody;
import com.escom.Creadordecasos.Exception.UserAlreadyExistsException;
import com.escom.Creadordecasos.Service.AuthService;
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

    /**
     * Servicio de usuario
     */
    private final AuthService authService;

    /**
     * Registra un nuevo estudiante en el sistema
     *
     * @param registrationBody Estructura que contiene al estudiante a crear
     * @return Http status CREATED si se registro correctamente,
     * http status CONFLICT si las credenciales ya estan registradas
     */
    @PostMapping("/registerStudent")
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
    @PostMapping("/registerTeacher")
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
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody
                                               LoginBody loginBody) {
        String jwt = null;

        jwt = authService.loginUser(loginBody);
        LoginResponse response = new LoginResponse();
        if (jwt == null) {
            response.setSuccess(false);
            response.setFailureReason("Wrong Credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }

        response.setJwt(jwt);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

}
