package com.escom.Creadordecasos.Service.Auth;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthResponse {
    private String jwt;
    private boolean success;
    private String failureReason;
}
