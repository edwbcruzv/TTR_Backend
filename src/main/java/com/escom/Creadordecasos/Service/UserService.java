package com.escom.Creadordecasos.Service;

import com.escom.Creadordecasos.Controller.Bodies.RegistrationBody;
import com.escom.Creadordecasos.Controller.Responses.AuthResponse;
import com.escom.Creadordecasos.Exception.*;
import com.escom.Creadordecasos.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Servicio para los usuarios
 */
@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private AuthService authService;

    private final UserRepository userRepository;
    public ResponseEntity<AuthResponse> registerAdmin(RegistrationBody registrationBody) {

        AuthResponse res;
        if (userRepository.findByEmail(registrationBody.getEmail()).isPresent()
                    || userRepository.findByUsername(registrationBody.getUsername()).isPresent()) {
            res = AuthResponse.builder()
                    .jwt(null)
                    .success(false)
                    .failureReason("El correo o usuario ya existen, no se puede crear otro. ")
                    .build();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
        }else{
            res = authService.register(registrationBody);
        }
        System.out.println("Teeeeeeeeeeeeeeermina");
        return ResponseEntity.ok(res);

    }

}
