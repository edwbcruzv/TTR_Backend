package com.escom.CreadorPracticas.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // genera setters y getters
@AllArgsConstructor // genera constructor con todos los atributos
@NoArgsConstructor // Constructor sin parametros
@Entity
@Builder
@Table(name = "instripciones")
public class Inscripcion {
    @EmbeddedId
    private InscripcionKey inscripcionKey;

    @ManyToOne(targetEntity = Estudiante.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "estudiante_username", referencedColumnName = "username", insertable = false, updatable = false)
    private Estudiante estudiante;

    @ManyToOne(targetEntity = Grupo.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "grupo_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Grupo grupo;

    @Column
    private float calificacion;

}
