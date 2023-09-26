package com.escom.Creadordecasos.Service;

import com.escom.Creadordecasos.Controller.Bodies.RegistrationBody;
import com.escom.Creadordecasos.Controller.Bodies.SendingMessageBody;
import com.escom.Creadordecasos.Controller.Bodies.UpdateBody;
import com.escom.Creadordecasos.Dto.MessageDto;
import com.escom.Creadordecasos.Dto.UserDto;
import com.escom.Creadordecasos.Entity.User;
import com.escom.Creadordecasos.Exception.BadUpdateRequestException;
import com.escom.Creadordecasos.Exception.SameSenderAndRecvException;
import com.escom.Creadordecasos.Exception.UserAlreadyExistsException;
import com.escom.Creadordecasos.Exception.UserNotFoundException;
import com.escom.Creadordecasos.Mapper.MessageMapper;
import com.escom.Creadordecasos.Mapper.UserMapper;
import com.escom.Creadordecasos.Repository.UserRepository;
import com.escom.Creadordecasos.Util.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Servicio para los usuarios
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final MessageService messageService;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;


    //////////////////////////////////////
    // Metodos para usuarios en general //
    //////////////////////////////////////

    /**
     * Almacena un nuevo mensaje en la base de datos
     *
     * @param sendingMessageBody Estructura del mensaje a guardar
     * @return True si se envio correctamente, false de lo contrario
     * @throws SameSenderAndRecvException Si el usuario al que lo envia es el mismo que el que lo recibe
     * @throws UserNotFoundException      Si el usuario al que lo envia no existe en la BD
     */
    public boolean sendMessage(SendingMessageBody sendingMessageBody)
            throws SameSenderAndRecvException, UserNotFoundException {
        Long userRecvId = sendingMessageBody.getRecvUserId();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userAuth = (User) authentication.getPrincipal();

        if (userRecvId == userAuth.getId()) {
            throw new SameSenderAndRecvException();
        }

        if (!userRepository.existsById(userRecvId)) {
            throw new UserNotFoundException();
        }

        MessageDto msgDto = new MessageDto();
        TimeZone mexicoTimeZone = TimeZone.getTimeZone("America/Mexico_City");
        msgDto.setSendingDate(new Date(System.currentTimeMillis() + mexicoTimeZone.getRawOffset()));
        msgDto.setSenderUserId(userAuth.getId());
        msgDto.setRecvUserId(userRecvId);
        msgDto.setContent(sendingMessageBody.getContent());
        // TODO: Validar que se puedan enviar los mensajes entre los usuarios.
        return messageService.createMessage(msgDto);
    }

    /**
     * Obtiene todos los mensajes enviados y recibidos que tiene con un usuario especifico
     *
     * @param id Id del usuario del que se desean obtener los mensajes
     * @return Lista de mensajes dto que representan los mensajes de la conversación
     * @throws UserNotFoundException      Si el usuario con el id especificado no existe en la BD
     * @throws SameSenderAndRecvException Si el id del usuario especifico es el mismo al autenticado
     */
    public List<MessageDto> getMessagesFrom(Long id) throws UserNotFoundException, SameSenderAndRecvException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userAuth = (User) authentication.getPrincipal();

        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }

        if (userAuth.getId() == id) {
            throw new SameSenderAndRecvException();
        }

        return messageService
                .getMessagesFromUserId(userAuth.getId()).stream()
                .filter(msg -> msg.getSenderUserId() == id || msg.getRecvUserId() == id)
                .toList();
    }

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
    public UserDto getUserById(Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        return userMapper.toUserDto(optionalUser.get());
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
    public UserDto updateUser(UpdateBody updateBody) throws UserNotFoundException {
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
