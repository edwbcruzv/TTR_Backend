package com.escom.Creadordecasos.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data // genera setters y getters
@AllArgsConstructor // genera constructor con todos los atributos
@NoArgsConstructor // Constructor sin parametros
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // me permite heredar o generalizar, separa en dos tablas en la bd
public class Usuario {

    @Id// lo define como el  Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es como el autoincrement
    private Long Id;

    @Column
    private String Username;

    @Column
    private String PasswordHash;

    @Column
    private String Rol;

    @Column
    private String Email;

    @Column
    private String Nombre;

    @Column
    private String ApellidoPaterno;

    @Column
    private String ApellidoMaterno;

    @Temporal(TemporalType.DATE)
    @Past
    private Date FechaNacimiento;

    @OneToMany(mappedBy = "DestinatarioId")
    private List<Mensaje> MensajesRecibidos;

    @OneToMany(mappedBy = "RemitenteId")
    private List<Mensaje> MensajesEnviados;

}
