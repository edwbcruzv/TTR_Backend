package com.escom.Creadordecasos.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data // genera setters y getters
@SuperBuilder
@AllArgsConstructor // genera constructor con todos los atributos
@NoArgsConstructor // Constructor sin parametros
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
@Inheritance(strategy = InheritanceType.JOINED) // me permite heredar o generalizar, separa en dos tablas en la bd
public class Usuario implements UserDetails {

    @Id// lo define como el  Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es como el autoincrement
    private Long id;

    @Column
    private String username;

    @Column
    private String password_hash;

    @Column
    private String rol;

    @Column
    private String email;

    @Column
    private String nombre;

    @Column
    private String apellido_paterno;

    @Column
    private String apellido_materno;

    @Column
    private LocalDate fecha_nacimiento;

    @OneToMany(targetEntity = Mensaje.class,fetch = FetchType.LAZY,mappedBy = "destinatario_id")
    private List<Mensaje> mensajes_recibidos;

    @OneToMany(targetEntity = Mensaje.class,fetch = FetchType.LAZY,mappedBy = "remitente_id")
    private List<Mensaje> mensajes_enviados;

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
