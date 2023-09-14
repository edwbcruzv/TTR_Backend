package com.escom.Creadordecasos.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
}
