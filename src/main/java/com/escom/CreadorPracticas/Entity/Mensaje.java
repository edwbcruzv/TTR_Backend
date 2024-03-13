package com.escom.CreadorPracticas.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data // genera setters y getters
@AllArgsConstructor // genera constructor con todos los atributos
@NoArgsConstructor // Constructor sin parametros
@Entity
@Builder
public class Mensaje {

    @Id// lo define como el  Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es como el autoincrement
    private Long id;

    @NotNull
    @Column
    private String mensaje;

    @NotNull
    @Column
    private LocalDateTime fechaEnvio;

    @ManyToOne(targetEntity = Usuario.class,fetch = FetchType.LAZY)
    private Usuario destinatario;

    @ManyToOne(targetEntity = Usuario.class,fetch = FetchType.LAZY)
    private Usuario remitente;

}