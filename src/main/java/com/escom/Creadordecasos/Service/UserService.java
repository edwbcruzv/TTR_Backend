package com.escom.Creadordecasos.Service;

import com.escom.Creadordecasos.Dto.RegistrationBody;
import com.escom.Creadordecasos.Dto.UpdateBody;
import com.escom.Creadordecasos.Dto.UserDto;
import com.escom.Creadordecasos.Entity.User;
import com.escom.Creadordecasos.Exception.BadUpdateRequestException;
import com.escom.Creadordecasos.Exception.UserAlreadyExistsException;
import com.escom.Creadordecasos.Exception.UserNotFoundException;
import com.escom.Creadordecasos.Mapper.UserMapper;
import com.escom.Creadordecasos.Repository.UserRepository;
import com.escom.Creadordecasos.Util.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para los usuarios
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    //////////////////////////////////////
    // Metodos para usuarios en general //
    //////////////////////////////////////

    //////////////////////////////
    // Metodos para estudiantes //
    //////////////////////////////

    //////////////////////////////////
    // Metodos para administradores //
    //////////////////////////////////

    /**
     * Registra un administrador en la aplicación
     *
     * @param registrationBody
     * @return
     * @throws UserAlreadyExistsException
     */
    public UserDto registerAdmin(RegistrationBody registrationBody) throws UserAlreadyExistsException {
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
        user.setRol(Rol.ADMIN);
        return userMapper.toUserDto(userRepository.save(user));
    }

    /**
     * Obtiene una lista con todos los usuarios (Exceptuando a el mismo)
     *
     * @return
     */
    public List<UserDto> getAllUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userAuthenticated = (User) authentication.getPrincipal();

        List<UserDto> userDtos = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            if (user.getId().equals(userAuthenticated.getId())) {
                continue;
            }
            userDtos.add(userMapper.toUserDto(user));
        }


        return userDtos;
    }


    /**
     * Obtiene un usuario dado un id
     *
     * @param id
     * @return
     */
    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toUserDto);
    }

    /**
     * Borra un usuario dado un id
     *
     * @param id
     * @throws UserNotFoundException
     */
    public void deleteUser(Long id) throws UserNotFoundException {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException();
        }

        userRepository.deleteById(id);
    }

    /**
     * Actualiza un usuario
     *
     * @param updateBody
     * @return
     * @throws BadUpdateRequestException
     */
    public UserDto updateUser(UpdateBody updateBody) {
        Optional<User> optionalUser = userRepository.findById(updateBody.getId());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = optionalUser.get();

        user.setUsername(updateBody.getUsername());
        user.setEmail(updateBody.getEmail());
        user.setFirstName(updateBody.getFirstName());
        user.setLastName(updateBody.getLastName());
        user.setMiddleName(updateBody.getMiddleName());

        if (updateBody.getPassword() != null && !updateBody.getPassword().isEmpty())
            user.setPassword(passwordEncoder.encode(updateBody.getPassword()));


        return userMapper.toUserDto(userRepository.save(user));
    }
}
