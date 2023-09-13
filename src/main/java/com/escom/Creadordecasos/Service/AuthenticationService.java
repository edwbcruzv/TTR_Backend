package com.escom.Creadordecasos.Service;

import com.escom.Creadordecasos.Dto.AuthenticationRequest;
import com.escom.Creadordecasos.Dto.AuthenticationResponse;
import com.escom.Creadordecasos.Dto.RegistrationRequest;
import com.escom.Creadordecasos.Entity.User;
import com.escom.Creadordecasos.Util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    public AuthenticationResponse login( AuthenticationRequest authRequest ) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
           authRequest.getUsername(),
           authRequest.getPassword()
        );

        authenticationManager.authenticate( authToken );

        User user = userService.findUserByUsername( authRequest.getUsername() ).get();

        String jwt = jwtService.generateToken( user, generateExtraClaims(user) );

        return new AuthenticationResponse(jwt);
    }

    public AuthenticationResponse registerAsStudent( RegistrationRequest request ) {
        User user = User.builder()
                .username( request.getUsername() )
                .email( request.getEmail() )
                .hashedPassword( passwordEncoder.encode( request.getPassword() ) )
                .firstName( request.getFirstName() )
                .middleName( request.getMiddleName() )
                .lastName( request.getLastName() )
                .role( Role.STUDENT )
                .build();
        userService.createUser( user );
        return AuthenticationResponse.builder()
                .jwt( jwtService.generateToken( user, generateExtraClaims( user ) ) )
                .build();
    }

    public AuthenticationResponse registerAsTeacher(RegistrationRequest request) {
        User user = User.builder()
                .username( request.getUsername() )
                .email( request.getEmail() )
                .hashedPassword( passwordEncoder.encode( request.getPassword() ) )
                .firstName( request.getFirstName() )
                .middleName( request.getMiddleName() )
                .lastName( request.getLastName() )
                .role( Role.TEACHER )
                .build();
        userService.createUser( user );
        return AuthenticationResponse.builder()
                .jwt( jwtService.generateToken( user, generateExtraClaims( user ) ) )
                .build();
    }


    private Map<String, Object> generateExtraClaims( User user ) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put( "name", user.getFirstName() );
        extraClaims.put( "role", user.getRole().name() );
        extraClaims.put( "permissions", user.getAuthorities() );

        return extraClaims;
    }
}
