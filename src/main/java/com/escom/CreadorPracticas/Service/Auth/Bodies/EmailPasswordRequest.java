package com.escom.CreadorPracticas.Service.Auth.Bodies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailPasswordRequest {
    private String email;
}
