package com.escom.Creadordecasos.Service;

import com.escom.Creadordecasos.Dto.AuthenticationRequest;
import com.escom.Creadordecasos.Dto.AuthenticationResponse;
import com.escom.Creadordecasos.Dto.RegistrationRequest;
import com.escom.Creadordecasos.Dto.Response;
import com.escom.Creadordecasos.Entity.User;
import com.escom.Creadordecasos.Util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Response<AuthenticationResponse> login(AuthenticationRequest authRequest) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(),
                    authRequest.getPassword()
            );

            Authentication authentication = authenticationManager.authenticate(authToken);

            User user = userService.findUserByUsername(authRequest.getUsername()).orElse(null);

            if (user == null) {
                return Response.<AuthenticationResponse>badRequest("Usuario no encontrado", null);
            }

            String jwt = jwtService.generateToken(user, generateExtraClaims(user));

            return Response.<AuthenticationResponse>success(new AuthenticationResponse(jwt), "Autenticación exitosa");
        } catch (AuthenticationException e) {
            return Response.<AuthenticationResponse>badRequest("Autenticación fallida", null);
        }
    }

    public Response<AuthenticationResponse> registerAsStudent(RegistrationRequest request) {
        try {
            User user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .hashedPassword(passwordEncoder.encode(request.getPassword()))
                    .firstName(request.getFirstName())
                    .middleName(request.getMiddleName())
                    .lastName(request.getLastName())
                    .role(Role.STUDENT)
                    .build();

            userService.createUser(user);

            String jwt = jwtService.generateToken(user, generateExtraClaims(user));

            return Response.<AuthenticationResponse>success(new AuthenticationResponse(jwt), "Registro exitoso");
        } catch (Exception e) {
            return Response.<AuthenticationResponse>badRequest("Error en el registro", null);
        }
    }

    public Response<AuthenticationResponse> registerAsTeacher(RegistrationRequest request) {
        try {
            User user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .hashedPassword(passwordEncoder.encode(request.getPassword()))
                    .firstName(request.getFirstName())
                    .middleName(request.getMiddleName())
                    .lastName(request.getLastName())
                    .role(Role.TEACHER)
                    .build();

            userService.createUser(user);

            String jwt = jwtService.generateToken(user, generateExtraClaims(user));

            return Response.<AuthenticationResponse>success(new AuthenticationResponse(jwt), "Registro exitoso");
        } catch (Exception e) {
            return Response.<AuthenticationResponse>badRequest("Error en el registro", null);
        }
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getFirstName());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("permissions", user.getAuthorities());

        return extraClaims;
    }
}

