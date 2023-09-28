package com.escom.Creadordecasos.Controller;


import com.escom.Creadordecasos.Controller.Bodies.SendingMessageBody;
import com.escom.Creadordecasos.Dto.MessageDto;
import com.escom.Creadordecasos.Exception.AuthenticationNotExistsException;
import com.escom.Creadordecasos.Exception.SameSenderAndRecvException;
import com.escom.Creadordecasos.Exception.UserNotFoundException;
import com.escom.Creadordecasos.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para los usuarios en general
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "User", description = "The User API. Contains all the operations that can be performed by all the types of user.")
public class UserController {

    private final UserService userService;

    /**
     * Envia un mensaje a un usuario del sistema
     *
     * @param sendingMessageBody Estructura del mensaje a enviar
     * @return Un http status
     * 202 si hubo exito
     * 404 si no se encontro el usuario a enviar
     * 409 si el usuario receptor es el mismo que el remitente
     */
    @PostMapping("/send-msg")
    public ResponseEntity sendMessage(@Valid @RequestBody
                                      SendingMessageBody sendingMessageBody) {
        try {
            userService.sendMessage(sendingMessageBody);
            return ResponseEntity.ok().build();
        } catch (SameSenderAndRecvException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtiene una lista(conversacion) con los mensajes que hay con un usuario especifico
     *
     * @param id Id del usuario especifico
     * @return Lista de los mensajes encontrados
     * http status 404 si no se encontro el usuario especificados
     * http status 406 si el usuario especificado es el mismo que el que hace la peticion
     */
    @Operation(summary = "Get Messages From an User", description = "Get messages form a conversation")
    @GetMapping("/read-msg/{id}")
    public ResponseEntity<List<MessageDto>> getMessagesFromUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getMessagesFrom(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (SameSenderAndRecvException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (AuthenticationNotExistsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
