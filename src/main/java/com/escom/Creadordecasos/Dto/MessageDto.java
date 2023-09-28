package com.escom.Creadordecasos.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Clase Dto para mensaje
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDto {

    private Long id;

    private String content;

    private Date sendingDate;

    private Long senderUserId;

    private Long recvUserId;
}
