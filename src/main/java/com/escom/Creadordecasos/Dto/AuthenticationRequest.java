package com.escom.Creadordecasos.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
