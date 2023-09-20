package com.escom.Creadordecasos.Controller;

import com.escom.Creadordecasos.Dto.AuthenticationRequest;
import com.escom.Creadordecasos.Dto.AuthenticationResponse;
import com.escom.Creadordecasos.Dto.RegistrationRequest;
import com.escom.Creadordecasos.Dto.Response;
import com.escom.Creadordecasos.Service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PreAuthorize("permitAll")
    @PostMapping(value = "/authenticate")
    public @ResponseBody Response<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authRequest) {

        return authenticationService.login(authRequest);
    }

    @PreAuthorize("permitAll")
    @PostMapping(value = "/register-student")
    public @ResponseBody Response<AuthenticationResponse> registerAsStudent(@RequestBody @Valid RegistrationRequest regRequest) {

        return authenticationService.registerAsStudent(regRequest);
    }

    @PreAuthorize("permitAll")
    @PostMapping(value = "/register-teacher")
    public @ResponseBody Response<AuthenticationResponse> registerAsTeacher(@RequestBody @Valid RegistrationRequest regRequest) {

        return authenticationService.registerAsTeacher(regRequest);
    }
}

