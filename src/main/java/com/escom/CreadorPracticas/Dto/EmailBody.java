package com.escom.CreadorPracticas.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailBody {
    private String email;
    private String content;
    private String subject;
}
