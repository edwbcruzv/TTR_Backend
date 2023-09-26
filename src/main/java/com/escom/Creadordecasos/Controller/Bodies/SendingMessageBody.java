package com.escom.Creadordecasos.Controller.Bodies;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SendingMessageBody {

    private Long recvUserId;

    @NotBlank
    @NotNull
    @Size(max = 1000)
    private String content;
}
