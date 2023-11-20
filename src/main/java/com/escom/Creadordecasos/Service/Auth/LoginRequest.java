package com.escom.Creadordecasos.Service.Auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
