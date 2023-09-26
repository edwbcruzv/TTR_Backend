package com.escom.Creadordecasos.Controller.Bodies;

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
