package com.escom.Creadordecasos.Controller;

import com.escom.Creadordecasos.Controller.Bodies.RegistrationBody;
import com.escom.Creadordecasos.Controller.Bodies.UpdateBody;
import com.escom.Creadordecasos.Dto.UserDto;
import com.escom.Creadordecasos.Exception.UserAlreadyExistsException;
import com.escom.Creadordecasos.Exception.UserNotFoundException;
import com.escom.Creadordecasos.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para los administradores
 */
@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    /**
     * Servicio de usuario
     */
    private final UserService userService;

    /**
     * Registra un nuevo admin en la aplicación
     *
     * @param registrationBody Estructura del admin a crear
     * @return Http code CREATED si se registra correctamente, CONFLICT si es que ya existe el usuario
     */
    @PostMapping("/register-admin")
    public ResponseEntity registerAdmin(@Valid @RequestBody
                                        RegistrationBody registrationBody) {
        try {
            userService.registerAdmin(registrationBody);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Devuelve una lista con todos los usuarios en la BD (Exceptuando al admin que la solicitó)
     *
     * @return Lista con los usuarios
     */
    @GetMapping("/get-users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Devuelve un usuario
     *
     * @param id Id del usuario a buscar
     * @return El usuario si es que se encontró, Http code NOT FOUND si no
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un usuario de la BD
     *
     * @param id Id del usuario a eliminar
     * @return Http code OK si se elimino correctamente, NOT FOUND si no se encontro
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Actualiza los datos de un usuario
     *
     * @param updateBody Estructura con los datos del usuario con actualizaciones
     * @return Usuario actualizado si se actualizó correctamente,
     * http code NOT FOUND si no existe el usuario,
     * http code BAD REQUEST si el updateBody esta mal estructurado,
     * http code CONFLICT si otro usuario ya tiene el username/email nuevo
     */
    @PatchMapping
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UpdateBody updateBody) {
        try {
            UserDto response = userService.updateUser(updateBody);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
