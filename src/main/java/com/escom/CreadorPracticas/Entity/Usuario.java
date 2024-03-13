package com.escom.CreadorPracticas.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data // genera setters y getters
@SuperBuilder
@AllArgsConstructor // genera constructor con todos los atributos
@NoArgsConstructor // Constructor sin parametros
@Entity
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "Username")})
@Inheritance(strategy = InheritanceType.JOINED) // me permite heredar o generalizar, separa en dos tablas en la bd
public class Usuario implements UserDetails {

    @Id
    @Column
    private String username;

    @NotNull
    @Column
    private String rol;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column
    private String passwordHash;

    @NotNull
    @Column
    private String nombre;

    @NotNull
    @Column
    private String apellidoPaterno;

    @NotNull
    @Column
    private String apellidoMaterno;

    @Column
    private LocalDate fechaNacimiento;

    @OneToMany(targetEntity = Mensaje.class,fetch = FetchType.LAZY,mappedBy = "destinatario")
    private List<Mensaje> mensajeRecibidos;

    @OneToMany(targetEntity = Mensaje.class,fetch = FetchType.LAZY,mappedBy = "remitente")
    private List<Mensaje> mensajesEnviados;


    // los siguientes metodos son de UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
