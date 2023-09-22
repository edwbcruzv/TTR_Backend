package com.escom.Creadordecasos.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginBody {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
