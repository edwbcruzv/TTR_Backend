package com.escom.CreadorPracticas.Service.Auth.Bodies;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthResponse {
    private String jwt;
    private boolean success;
    private String rol;
    private String usernameSession;
    private String email;
    private String nombreSession;
    private Boolean isValidSession;
    private String failureReason;
}
