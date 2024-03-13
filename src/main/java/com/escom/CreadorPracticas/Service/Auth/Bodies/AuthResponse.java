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
    private String failureReason;
}
