package com.escom.Creadordecasos.Service.Auth.Bodies;

import com.escom.Creadordecasos.Util.Rol;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido_paterno;

    @NotBlank
    private String apellido_materno;

    private Date fecha_nacimiento;

    @NotBlank
    private String rol;

    private String cedula; //profesor

    private String escuela; // profesor

    private List<Integer> grupos; //profesor

    private String boleta; // Estudiante

    private Integer semestre; // Estudiante


}
