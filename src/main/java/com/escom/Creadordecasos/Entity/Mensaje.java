package com.escom.Creadordecasos.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data // genera setters y getters
@AllArgsConstructor // genera constructor con todos los atributos
@NoArgsConstructor // Constructor sin parametros
@Entity
@Builder
public class Mensaje {

    @Id// lo define como el  Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es como el autoincrement
    private Long id;

    @Column
    private String content;

    @Column
    private LocalDateTime fecha_envio;

    @ManyToOne(targetEntity = Usuario.class,fetch = FetchType.LAZY)
    private Usuario destinatario_id;

    @ManyToOne(targetEntity = Usuario.class,fetch = FetchType.LAZY)
    private Usuario remitente_id;

}