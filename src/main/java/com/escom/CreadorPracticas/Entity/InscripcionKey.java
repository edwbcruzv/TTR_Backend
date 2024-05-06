package com.escom.CreadorPracticas.Entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // genera setters y getters
@AllArgsConstructor // genera constructor con todos los atributos
@NoArgsConstructor // Constructor sin parametros
@Embeddable
@Builder
public class InscripcionKey {
    private String estudiante_username;
    private Long grupo_id;
}
