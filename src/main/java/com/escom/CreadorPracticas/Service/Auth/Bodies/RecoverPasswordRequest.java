package com.escom.CreadorPracticas.Service.Auth.Bodies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecoverPasswordRequest {
    private String password;
}
