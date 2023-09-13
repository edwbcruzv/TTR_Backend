package com.escom.Creadordecasos.Config.Security;

import com.escom.Creadordecasos.Config.Security.Filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    JwtAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception {
        http
                .csrf(
                        csrfConfigurer -> csrfConfigurer.disable()
                )
                .sessionManagement(
                        sessionManagmentConfig ->
                                sessionManagmentConfig.sessionCreationPolicy( SessionCreationPolicy.STATELESS )
                )
                .authenticationProvider( authenticationProvider )
                .addFilterBefore( authenticationFilter, UsernamePasswordAuthenticationFilter.class );

        return http.build();
    }
}
