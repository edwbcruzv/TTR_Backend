package com.escom.CreadorPracticas.Service.Auth.Bodies;

import com.escom.CreadorPracticas.Util.Rol;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthResponse {
    private String jwt;
    private boolean success;
    private Rol rol;
    private String usernameSession;
    private String email;
    private String nombreSession;
    private Boolean isValidSession;
    private String failureReason;
}
