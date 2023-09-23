package com.escom.Creadordecasos.Config;

import com.escom.Creadordecasos.Repository.UserRepository;
import com.escom.Creadordecasos.Security.JwtAuthenticationProvider;
import com.escom.Creadordecasos.Security.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Clase de configuración para la creación de Beans a utilizar
 */
@RequiredArgsConstructor
@Configuration
public class ApplicationConfig {
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserRepository userRepository;

    /**
     * Bean de Password Encoder para inyectar
     *
     * @return Implementación BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean de JwtRequestFilter para inyeccion
     *
     * @return Implementación JwtRequestFiletr
     */
    @Bean
    public JwtRequestFilter jwtAuthFilter() {
        return new JwtRequestFilter();
    }
}
