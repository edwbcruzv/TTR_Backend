package com.escom.Creadordecasos.Controller;

import com.escom.Creadordecasos.Dto.AuthenticationRequest;
import com.escom.Creadordecasos.Dto.AuthenticationResponse;
import com.escom.Creadordecasos.Dto.RegistrationRequest;
import com.escom.Creadordecasos.Service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    AuthenticationService authenticationService;

    @PreAuthorize("permitAll")
    @PostMapping(value = "authenticate")
    public ResponseEntity<AuthenticationResponse> login( @RequestBody @Valid
                                                         AuthenticationRequest authRequest ) {
        AuthenticationResponse jwtDto = authenticationService.login( authRequest );

        return ResponseEntity.ok( jwtDto );
    }

    @PreAuthorize("permitAll")
    @PostMapping(value = "register-student")
    public ResponseEntity<AuthenticationResponse> registerAsStudent( @RequestBody @Valid
                                                                     RegistrationRequest regRequest ) {
        AuthenticationResponse jwtDto = authenticationService.registerAsStudent( regRequest );
        return ResponseEntity.ok( jwtDto );
    }

    @PreAuthorize("permitAll")
    @PostMapping(value = "register-teacher")
    public ResponseEntity<AuthenticationResponse> registerAsTeacher( @RequestBody @Valid RegistrationRequest regRequest ) {
        AuthenticationResponse jwtDto = authenticationService.registerAsTeacher( regRequest );
        return ResponseEntity.ok( jwtDto );
    }
}

