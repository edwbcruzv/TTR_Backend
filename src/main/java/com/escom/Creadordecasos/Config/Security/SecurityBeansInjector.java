package com.escom.Creadordecasos.Config.Security;

import com.escom.Creadordecasos.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityBeansInjector {
    @Autowired
    private UserRepository userRepository;

    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration authenticationConfiguration )
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService( userDetailService() );
        provider.setPasswordEncoder( passwordEncoder() );

        return provider;
    }

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private UserDetailsService userDetailService() {
        return username -> {
            return userRepository.findByUsername( username )
                    .orElseThrow( () -> new RuntimeException( "User Not Found" ) );
        };
    }
}
