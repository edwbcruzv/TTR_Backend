package com.escom.Creadordecasos.Dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AuthenticationResponse {
    private String jwt;
}
