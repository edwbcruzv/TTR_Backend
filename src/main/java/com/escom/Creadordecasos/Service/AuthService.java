package com.escom.Creadordecasos.Service;

import com.escom.Creadordecasos.Controller.Bodies.RegistrationBody;
import com.escom.Creadordecasos.Controller.Responses.AuthResponse;
import com.escom.Creadordecasos.Entity.Usuario;
import com.escom.Creadordecasos.Repository.UserRepository;
import com.escom.Creadordecasos.Security.JwtAuthenticationProvider;
import com.escom.Creadordecasos.Util.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Servicio para la autenticación
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;


    public AuthResponse register(RegistrationBody registrationBody){
        Date now = new Date();

        try {
            Usuario usuario = Usuario.builder()
                    .username(registrationBody.getUsername())
                    .email(registrationBody.getEmail())
                    .password_hash(registrationBody.getPassword())
                    .nombre(registrationBody.getNombre())
                    .apellido_paterno(registrationBody.getApellido_paterno())
                    .apellido_materno(registrationBody.getApellido_materno())
                    .fecha_nacimiento(now)
                    .rol(Rol.ADMIN)
                    .build();

            userRepository.save(usuario);

            return AuthResponse.builder()
                    .jwt(jwtAuthenticationProvider.createToken(usuario))
                    .success(true)
                    .failureReason("Usuario Creado")
                    .build();

        }catch (Exception e){
            return AuthResponse.builder()
                    .jwt(null)
                    .success(false)
                    .failureReason("Error: "+ e.toString())
                    .build();
        }
    }

    /**
     * Autentica al usuario y devuelve un jwt
     *
     * @param loginBody
     * @return
     */
    /*
    public String loginUser(LoginBody loginBody) throws UserNotFoundException, WrongPasswordException {
        Optional<User> optionalUser = userRepository.findByUsernameIgnoreCase(loginBody.getUsername());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        User user = optionalUser.get();
        if (!passwordEncoder.matches(loginBody.getPassword(), user.getPassword())) {
            throw new WrongPasswordException();
        }

        return jwtAuthenticationProvider.createToken(user);
    }*/
}
