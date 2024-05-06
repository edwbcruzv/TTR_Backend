package com.escom.CreadorPracticas.Dto;

import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioDTO implements Serializable {
    private String username;
    private String rol;
    private String email;
    private String passwordHash;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
}
