package com.escom.Creadordecasos.Service.Profesores.Bodies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateProfesorRequest {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private LocalDate fecha_nacimiento;
    private String cedula;
    private String escuela;

}
