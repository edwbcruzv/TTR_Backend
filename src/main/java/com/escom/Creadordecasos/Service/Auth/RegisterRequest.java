package com.escom.Creadordecasos.Service.Auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

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
}
