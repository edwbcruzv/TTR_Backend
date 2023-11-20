package com.escom.Creadordecasos.Dto;

import com.escom.Creadordecasos.Entity.Usuario;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase Dto para mensaje
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MensajeDTO implements Serializable {

    private Long id;

    private String content;

    private Date fecha_envio;

    private Usuario destinatario_id;

    private Usuario remitente_id;
}
