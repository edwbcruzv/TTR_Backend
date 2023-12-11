package com.escom.Creadordecasos.Service.Estudiantes.Bodies;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
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
public class UpdateEstudianteRequest {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String nombre;

    private String apellido_paterno;

    private String apellido_materno;

    private LocalDate fecha_nacimiento;

    private String boleta;

    private Integer semestre;

}
