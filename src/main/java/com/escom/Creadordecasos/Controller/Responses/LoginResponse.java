package com.escom.Creadordecasos.Controller.Responses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginResponse {
    private String jwt;
    private boolean success;
    private String failureReason;
}
