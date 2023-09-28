package com.escom.Creadordecasos.Service;

import com.escom.Creadordecasos.Controller.Bodies.LoginBody;
import com.escom.Creadordecasos.Controller.Bodies.RegistrationBody;
import com.escom.Creadordecasos.Dto.UserDto;
import com.escom.Creadordecasos.Entity.User;
import com.escom.Creadordecasos.Exception.UserAlreadyExistsException;
import com.escom.Creadordecasos.Exception.UserNotFoundException;
import com.escom.Creadordecasos.Exception.WrongPasswordException;
import com.escom.Creadordecasos.Mapper.UserMapper;
import com.escom.Creadordecasos.Repository.UserRepository;
import com.escom.Creadordecasos.Security.JwtAuthenticationProvider;
import com.escom.Creadordecasos.Util.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio para la autenticación
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    /**
     * Registra un nuevo usuario en la BD, con el rol de estudiante por defecto
     *
     * @param registrationBody
     * @return
     * @throws UserAlreadyExistsException
     */
    public UserDto registerStudent(RegistrationBody registrationBody)
            throws UserAlreadyExistsException {
        if (userRepository.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
                || userRepository.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        User user = new User();
        user.setUsername(registrationBody.getUsername());
        user.setEmail(registrationBody.getEmail());
        user.setPassword(passwordEncoder.encode(registrationBody.getPassword()));
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setMiddleName(registrationBody.getMiddleName());
        user.setRol(Rol.STUDENT);
        return userMapper.toUserDto(userRepository.save(user));
    }

    /**
     * Registra un nuevo usuario en la BD, con el rol de maestro por defecto
     *
     * @param registrationBody
     * @return
     * @throws UserAlreadyExistsException
     */
    public UserDto registerTeacher(RegistrationBody registrationBody)
            throws UserAlreadyExistsException {
        if (userRepository.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
                || userRepository.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        User user = new User();
        user.setUsername(registrationBody.getUsername());
        user.setEmail(registrationBody.getEmail());
        user.setPassword(passwordEncoder.encode(registrationBody.getPassword()));
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setMiddleName(registrationBody.getMiddleName());
        user.setRol(Rol.TEACHER);
        return userMapper.toUserDto(userRepository.save(user));
    }

    /**
     * Autentica al usuario y devuelve un jwt
     *
     * @param loginBody
     * @return
     */
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
    }
}
